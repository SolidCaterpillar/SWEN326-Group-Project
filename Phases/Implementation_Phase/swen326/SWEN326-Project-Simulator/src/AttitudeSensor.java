import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AttitudeSensor {
	private double attitude;
	private Timer timer;
    private Random random;
	
	public AttitudeSensor() {
		this.attitude = 0; // 10knots
		this.timer = new Timer();
	    this.random = new Random();
	}
	
	 public void start() {
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                System.out.println("Attitude: " + attitude + " knots");
	            }
	        }, 0, 500);  // Update every second
	    }
	
	public void altitude(double alt) {
		attitude = alt;
	} 
	
	public double getAltitude() {
		return attitude; 	
	} 
}
