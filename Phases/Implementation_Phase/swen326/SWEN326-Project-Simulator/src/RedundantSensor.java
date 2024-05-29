import java.util.List;

public class RedundantSensor<T>  {
    private List<Sensor<T>> sensors;

    public RedundantSensor(List<Sensor<T>> sensors) {
        this.sensors = sensors;
    }
    
    public void setValue(double value) {
    	for(Sensor<T> sensor : sensors) {
    		sensor.setValue(value); 
    	}
    }
    
    public void simulate() {
    	for(Sensor<T> sensor : sensors) {
    		sensor.simulate(); 
    	}	
    }

    public Double getValue() {
        double value1 = sensors.get(0).getValue().doubleValue();
        double value2 = sensors.get(1).getValue().doubleValue();
        double value3 = sensors.get(2).getValue().doubleValue();

        // Apply 2oo3 mechanism with 20% tolerance
        if (agreeing(value1, value2) || agreeing(value1, value3)) {
            return value1;
        } else if (agreeing(value2, value3)) {
            return value2;
        } else {
            return value1;
        }
    }
        
    private boolean agreeing(double value1, double value2) {
        double tolerance = 0.2 * Math.max(Math.abs(value1), Math.abs(value2));
        //System.out.println("tolerance " +tolerance);
        //System.out.println("v1 " +value1);
        //System.out.println("v2 " +value2);
        return Math.abs(value1 - value2) <= tolerance;
    }
}
    
interface Sensor<T> {
	void setValue(double value);
	void simulate();
    Double getValue();
}
