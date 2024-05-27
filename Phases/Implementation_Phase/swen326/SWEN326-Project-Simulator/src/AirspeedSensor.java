import java.util.Random;

public class AirspeedSensor implements Sensor<Double>{
	private double airspeed;
	private double output;
    private Random random;

    public AirspeedSensor() {
        this.airspeed = 0.0; 
        this.output = 0.0;
        this.random = new Random();
    }
    
	@Override
	public void setValue(double value) {
		this.airspeed = value;
	}
	
	@Override
	public void simulate() {
	  	double percentRange = airspeed * 0.20;
    	double min = airspeed - percentRange;
    	double max = airspeed + percentRange;
    	this.output = min + (max - min) * random.nextDouble(); //generate a 20% range of random between the value of airspeed
	}

	@Override
	public Double getValue() {
		return this.output; 
	}
}
