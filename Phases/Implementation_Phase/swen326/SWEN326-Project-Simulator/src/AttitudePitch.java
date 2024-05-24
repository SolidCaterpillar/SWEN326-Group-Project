import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AttitudePitch{
    private double pitch;
    private double output;
    private Timer timer;
    private Random random;

    public AttitudePitch() {
    	this.output = 0.0;
        this.pitch = 0.0;
        this.timer = new Timer();
        this.random = new Random();
        start();
    }

    private void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                output = simulatePitch();
            }
        }, 0, 500);  // Update every 500 milliseconds
    }

    private double simulatePitch() {
     	double percentRange = pitch * 0.20;
    	double min = pitch - percentRange;
    	double max = pitch + percentRange;
        return min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of pitch
    }
}
