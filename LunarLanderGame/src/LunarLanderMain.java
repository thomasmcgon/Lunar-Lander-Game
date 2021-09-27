//based on an a design by Stuart Reges
   
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LunarLanderMain extends JFrame {

   public LunarLanderMain() {
   	// create frame and lander
      setSize(300, 500);
      setTitle("COMP 2100 Lunar Lander");
      Container contentPane = getContentPane();
      myLander = new LunarLander();
      myLander.reset();
   
   	// create and add info panel on bottom
      myInfo = new LunarInfoPanel(myLander);
      contentPane.add(myInfo, "South");
   	
   	// create and add lander picture
      myPicture = new LunarPicture(myLander);
      contentPane.add(myPicture, "Center");
   	
   	// create and add reset/thrust buttons at the top
      JPanel buttons = createButtonPanel();
      contentPane.add(buttons, "North");
   	
   	// create a timer and start it
      addTimer();
      myTimer.start();
   
      addWindowListener(
         new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
               System.exit(0);
            }
         });
   }
	
   /**
    * createButtonPanel provides the interaction components of the GUI
    * 
    * Ensures: creates and returns a panel with buttons for resetting the
    *          simulation and for applying thrust
    */
   private JPanel createButtonPanel() {
      JPanel buttons = new JPanel();
      JButton reset = new JButton("Reset");
      reset.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               myLander.reset();
               myTimer.restart();
               myInfo.update();
               myPicture.update();
            }
         });
      buttons.add(reset);
      JButton thrust = new JButton("Thrust");
      thrust.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               myLander.thrust();
            }
         });
      buttons.add(thrust);
      return buttons;
   }
	
   /**
    * addTimer facilites the real-time interaction of the game
    *
    * Ensures: creates a timer that calls the lander's tick
    *          method and updates the displays
    */
   private void addTimer() {
      ActionListener updater = 
         new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               myLander.tick();
               myInfo.update();
               myPicture.update();
               if (myLander.getAltitude() <= 0)
                  myTimer.stop();
            }
         };
      myTimer = new Timer(1000, updater);
   }
	
   private LunarLander myLander;   // lander to query
   private LunarInfoPanel myInfo;  // info panel
   private LunarPicture myPicture; // picture panel
   private Timer myTimer;          // timer

   public static void main(String[] args) {
      LunarLanderMain f = new LunarLanderMain();
      f.show();
   }
}