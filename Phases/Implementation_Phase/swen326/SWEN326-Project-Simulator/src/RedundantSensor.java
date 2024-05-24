import java.util.List;

public class RedundantSensor<T> {
    private List<T> sensors;

    public RedundantSensor(List<T> sensors) {
        if (sensors.size() != 3) {
            throw new IllegalArgumentException("2oo3 redundancy requires exactly 3 sensors.");
        }
        this.sensors = sensors;
    }

    public double getValue() {
    	T value1 = sensors.get(0);
    	T value2 = sensors.get(1);
    	T value3 = sensors.get(2);

    	
    	return 0;
    }
}
