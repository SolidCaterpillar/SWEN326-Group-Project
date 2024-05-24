import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AirspeedSensor {
	private double airspeed;
	private double output; 
    private Timer timer;
    private Random random;

    public AirspeedSensor() {
    	this.output = 0.0;
        this.airspeed = 0.0;
        this.timer = new Timer();
        this.random = new Random();
        start();
    }

    private void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	output = simulateAirspeed();
            }
        }, 0, 1000);  // Update every second
    }

    private double simulateAirspeed() {
    	double percentRange = airspeed * 0.20;
    	double min = airspeed - percentRange;
    	double max = airspeed + percentRange;
        return min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of airspeed
    }
	

}
