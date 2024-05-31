import java.util.Random;

public class Engine {
    private double thrust;
    private double output;
    private Random random;

    public Engine() {
        this.thrust = 0.0;
        this.random = new Random();
    }

    public void setValue(double value) {
		this.thrust = value;
	}

    public void simulate() {
    	double percentRange = thrust * 0.05;
    	double min = thrust - percentRange;
    	double max = thrust + percentRange;
    	this.output = min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of thrust
    }
    
    public Double getValue() {
		return this.output;
	}    
}
