import java.util.Random;


public class AttitudeRoll implements Sensor<Double>{
    private double roll;
    private double output;
    private Random random;

    public AttitudeRoll() {
        this.roll = 0.0;
        this.random = new Random();
    }

	@Override
	public void setValue(double value) {
		this.roll = value;
	}

	@Override
	public void simulate() {
		double percentRange = roll * 0.20;
    	double min = roll - percentRange;
    	double max = roll + percentRange;
    	this.output = min + (max - min) * random.nextDouble();  //generate a 20% range of random between the value of roll
	}

	@Override
	public Double getValue() {
		return this.output;
	}
}

