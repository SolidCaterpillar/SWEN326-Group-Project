import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AttitudeYaw{
    private double yaw;
    private double output;
    private Timer timer;
    private Random random;

    public AttitudeYaw() {
        this.yaw = 0.0;
        this.timer = new Timer();
        this.random = new Random();
        start();
    }

    private void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	output = simulateYaw();
            }
        }, 0, 500);  // Update every 500 milliseconds
    }

    private double simulateYaw() {
    	double percentRange = yaw * 0.20;
    	double min = yaw - percentRange;
    	double max = yaw + percentRange;
        return min + (max - min) * random.nextDouble();   //generate a 20% range of random between the value of yaw
    }
}

