import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class LunarPicture displays a graphical view of the lunar
 * lander's current altitude
 */
public class LunarPicture extends JPanel {

   /**
    * LunarPicture constructor 
    *
    * Ensures: given lander object is added to state of LunarPicture object
    */
   public LunarPicture(LunarLander lander) {
      myLander = lander;
   }
	
   /**
    * Update refreshes drawing of lander
    *
    * Ensures: picture is redrawn to account for current lander state
    */
   public void update() {
      repaint();
   }
	
   /**
    * paintComponent generates a visual depiction of the lander's current state
    *
    * Ensures: lander is drawn on user interface
    */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.darkGray);
      g.setColor(Color.magenta);
      int altitude = myLander.getAltitude();
      if (altitude <= 0 && myLander.getVelocity() > LunarLander.SAFE_LANDING) {
         Font f = new Font("Serif", Font.BOLD, Math.min(48, getHeight()));
         g.setFont(f);
         g.drawString("CRASH!", (getWidth() - g.getFontMetrics().stringWidth("CRASH!"))/2, (getHeight() + f.getSize())/2);
      } else {
         altitude = Math.max(0, altitude);
         int verticalSpace = getHeight() - 3 * BASE_HEIGHT - 4;
         int lowerLeftX = (getWidth() - BASE_WIDTH)/2;
         int lowerLeftY = 3 * BASE_HEIGHT + (LunarLander.INITIAL_ALTITUDE - myLander.getAltitude()) * verticalSpace/LunarLander.INITIAL_ALTITUDE + 2;
      	// draw legs
         g.drawLine(lowerLeftX, lowerLeftY, lowerLeftX, lowerLeftY - BASE_HEIGHT);
         g.drawLine(lowerLeftX + BASE_WIDTH - 1, lowerLeftY, lowerLeftX + BASE_WIDTH - 1, lowerLeftY - BASE_HEIGHT);
      	// draw struts
         g.drawLine(lowerLeftX - 3, lowerLeftY, lowerLeftX + 3, lowerLeftY);
         g.drawLine(lowerLeftX + BASE_WIDTH - 4, lowerLeftY, lowerLeftX + BASE_WIDTH + 2, lowerLeftY);
      	// draw middle
         g.fillRect(lowerLeftX, lowerLeftY - 2 * BASE_HEIGHT, BASE_WIDTH, BASE_HEIGHT);
      	// draw top
         g.fillOval(lowerLeftX, lowerLeftY - 3 * BASE_HEIGHT, BASE_WIDTH, BASE_HEIGHT);
      }
   }
	
   private LunarLander myLander; // lander to query
	
   public static final int BASE_HEIGHT = 20; // base height of 3 subfigures of lander
   public static final int BASE_WIDTH = 60;  // base width of lander figure
}