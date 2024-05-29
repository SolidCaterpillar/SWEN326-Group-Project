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
	
	@Override
	public void simulate() {
	  	double percentRange = thrust * 0.20;
    	double min = thrust - percentRange;
    	double max = thrust + percentRange;
    	
    	double num = 1000 * (thrust/100);    // current speed
    	double factor = random.nextDouble();     // add or subtract the update speed 
    	
    	double speedUpdate = factor > 0.5 ? min + (max - min)/2 : min - (max - min)/2;
     	this.output = num + speedUpdate; //generate a 20% range of random between the value of airspeed
	}

	@Override
	public Double getValue() {
		return this.output; 
	}
	
	public Double getSpeed() {
		return thrust;
	}
}
