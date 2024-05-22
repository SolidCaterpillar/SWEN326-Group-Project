public record Sensor(String name, double minValue, double maxValue, double threshold) {
	
	public double getValue() {
		double randomValue = Math.random();
        return minValue + (maxValue - minValue) * randomValue;
    }

    public boolean normalValue(double value) {
        return value >= minValue && value <= maxValue;
    }

    public boolean rapidChange(double oldValue, double newValue) {
        return Math.abs(newValue - oldValue) > threshold;
    }
}