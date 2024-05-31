import java.util.Random;

public class AttitudeYaw implements Sensor<Double>{
    private double yaw;
    private double output;
    private Random random;

    public AttitudeYaw() {
        this.yaw = 0.0;
        this.output = 0.0;
        this.random = new Random();
    }

	@Override
	public void setValue(double value) {
		this.yaw = value;
	}


	@Override
	public void simulate() {
		double percentRange = yaw * 0.20;
    	double min = yaw - percentRange;
    	double max = yaw + percentRange;
		this.output = min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of yaw
	}


	@Override
	public Double getValue() {
		return this.output;
	}
}

