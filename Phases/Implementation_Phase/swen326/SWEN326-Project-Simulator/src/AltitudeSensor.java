import java.util.Random;

public class AltitudeSensor implements Sensor<Double>{
    private double altitude;
    private double output;
    private Random random;

    public AltitudeSensor() {
        this.altitude = 0.0;
        this.output = 0.0;
        this.random = new Random();
    }

    @Override
	public void setValue(double value) {
    	this.altitude = value;
	}
    
	@Override
	public void simulate() {
	 	double percentRange = altitude * 0.20;
    	double min = altitude - percentRange;
    	double max = altitude + percentRange;
    	this.output =  min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of altitude
	}

	@Override
	public Double getValue() {
		return this.output; 
	}
}
