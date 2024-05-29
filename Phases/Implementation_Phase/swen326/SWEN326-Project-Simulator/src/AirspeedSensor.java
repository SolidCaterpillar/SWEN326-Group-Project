import java.util.Random;

public class AirspeedSensor implements Sensor<Double>{
	private double thrust;
	private double output;
    private Random random;

    public AirspeedSensor(double speed) {
        this.output = 0.0;
        this.random = new Random();
        this.thrust=speed;
    }
    
	@Override
	public void setValue(double value) {
		this.thrust = value;
	}
	
	public void setThrust(double d) {
		this.thrust = d;
	}
	@Override
	public void simulate() {
	  	double percentRange = thrust * 0.20;
    	double min = thrust - percentRange;
    	double max = thrust + percentRange;
    	this.output = (1000 + (min + (max - min) * random.nextDouble())) * (thrust/100); //generate a 20% range of random between the value of airspeed
	}

	@Override
	public Double getValue() {
		return this.output; 
	}
	
	public Double getSpeed() {
		return thrust;
	}
}
