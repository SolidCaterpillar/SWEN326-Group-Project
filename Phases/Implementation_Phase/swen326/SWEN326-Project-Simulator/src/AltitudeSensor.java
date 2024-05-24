import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AltitudeSensor{
    private double altitude;
    private double output; 
    private Timer timer;
    private Random random;

    public AltitudeSensor() {
        this.altitude = 0.0;
        this.timer = new Timer();
        this.random = new Random();
        start();
    }

    private void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                output = simulateAltitude();
            }
        }, 0, 500);  // Update every 500 milliseconds
    }

    private double simulateAltitude() {
       	double percentRange = altitude * 0.20;
    	double min = altitude - percentRange;
    	double max = altitude + percentRange;
        return min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of altitude
    }

  
}
