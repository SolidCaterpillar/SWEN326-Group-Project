import java.util.Random;


public class AttitudePitch implements Sensor<Double>{
    private double pitch;
    private double output;
    private Random random;

    public AttitudePitch() {
        this.pitch = 0.0;
        this.output = 0.0;
        this.random = new Random();
    }
    
	@Override
	public void setValue(double value) {
		this.pitch = value;
	}

	@Override
	public void simulate() {
	  	double percentRange = pitch * 0.20;
    	double min = pitch - percentRange;
    	double max = pitch + percentRange;
    	this.output =  min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of pitch
	}

	@Override
	public Double getValue() {
		return this.output;
	}
}
