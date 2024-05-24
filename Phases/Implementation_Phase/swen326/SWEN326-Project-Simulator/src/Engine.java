import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {
    private double thrust;
    private double output;
    private Timer timer;
    private Random random;

    public Engine() {
        this.thrust = 0.0;
        this.timer = new Timer();
        this.random = new Random();
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	output = simulateThrust();
                System.out.println("Thrust: " + thrust + " lbf");
            }
        }, 0, 1000);  // Update every second
    }

    private double simulateThrust() {
    	double percentRange = thrust * 0.20;
    	double min = thrust - percentRange;
    	double max = thrust + percentRange;
        return min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of thrust
    }
}
