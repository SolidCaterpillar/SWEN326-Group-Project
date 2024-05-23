import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AirspeedSensor {
	private double airspeed;
	private Timer timer;
    private Random random;
	
	public AirspeedSensor() {
		this.airspeed = 50; // 50knots
		this.timer = new Timer();
	    this.random = new Random();
	}
	
	 public void start() {
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                System.out.println("Airspeed: " + airspeed + " knots");
	            }
	        }, 0, 1000);  // Update every second
	    }
	
	public void accelerate(double thrust) {
		airspeed += thrust;
		if(airspeed > 500) {
			airspeed = 500; 
		}
	} 
	
	public double getAirSpeed() {
		return airspeed; 	
	} 

}
