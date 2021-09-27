public class LunarLander {
	
   public static final int INITIAL_VELOCITY = 40;   // meters/second
   public static final int INITIAL_ALTITUDE = 1000; // meters
   public static final int INITIAL_FUEL     = 25;   // thrusts
   public static final int GRAVITY          = 2;    // gravitational acceleration
													             // in meters/second/second
   public static final int THRUST           = 4;    // thrust acceleration in
													             // meters/second/second
   public static final int SAFE_LANDING     = 4;    // speed at which lander can
  
   int vel;
   int alt;
   int fuel;
   int thrust;
   public LunarLander() {
	   vel = INITIAL_VELOCITY;
	   alt = INITIAL_ALTITUDE;
	   fuel = INITIAL_FUEL;
	   thrust = 0;
   }
   
   public void reset() {
	   vel = INITIAL_VELOCITY;
	   alt = INITIAL_ALTITUDE;
	   fuel = INITIAL_FUEL;
   }
   
   public int getAltitude() {
	  return alt; 
   }
   
   public int getVelocity() {
	   return vel;
   }
   
   public int getFuel() {
	   return fuel;
   }
   
   public void thrust() {
	  thrust += 1; 
   }
   
   public void tick() {
	   vel += GRAVITY - (thrust * THRUST);
	   alt -= vel;
	   fuel -= thrust;
	   thrust = Math.min(thrust, 0);  
   }
   
}
