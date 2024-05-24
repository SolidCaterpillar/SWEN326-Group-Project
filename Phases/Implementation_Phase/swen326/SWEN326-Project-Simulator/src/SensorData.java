
public class SensorData {
	private RedundantSensor<AirspeedSensor> airspeedSensor;
    private RedundantSensor<AltitudeSensor> altitudeSensor;
    private RedundantSensor<AttitudePitch> attitudePitch;
    private RedundantSensor<AttitudeRoll> altitudeRoll;
    private RedundantSensor<AttitudeYaw> attitudeYaw;
    private Engine engine;

    public SensorData(RedundantSensor<AirspeedSensor> airspeedSensor, RedundantSensor<AltitudeSensor> altitudeSensor, RedundantSensor<AttitudePitch> attitudePitch, 
    		RedundantSensor<AttitudeRoll> altitudeRoll, RedundantSensor<AttitudeYaw> attitudeYaw, Engine engine) {
        this.airspeedSensor = airspeedSensor;
        this.altitudeSensor = altitudeSensor;
        this.attitudePitch = attitudePitch;
        this.altitudeRoll = altitudeRoll;
        this.attitudeYaw = attitudeYaw;
        this.engine = engine;
    }
}
