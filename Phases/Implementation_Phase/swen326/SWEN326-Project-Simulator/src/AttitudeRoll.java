import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AttitudeRoll {
    private double roll;
    private double output;
    private Timer timer;
    private Random random;

    public AttitudeRoll() {
    	this.output = 0.0;
        this.roll = 0.0;
        this.timer = new Timer();
        this.random = new Random();
        start();
    }

    private void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                output = simulateRoll();
            }
        }, 0, 500);  // Update every 500 milliseconds
    }

    private double simulateRoll() {
    	double percentRange = roll * 0.20;
    	double min = roll - percentRange;
    	double max = roll + percentRange;
        return min + (max - min) * random.nextDouble();  //generate a 20% range of random between the value of roll
    }

}

