package test;

import java.util.function.Supplier;

/**
 * The simulator will be simulating the following characteristics of an
 * aircraft:
 * 
 * 3.2 Sensor Data Simulation and Frequency 
 *     1. Airspeed Sensor: Data format:
 * knots (nautical miles per hour). Update frequency: every second. 
 *     2. Altitude
 * Sensor: Data format: feet above mean sea level (AMSL). Update frequency:
 * every 500 milliseconds. 
 *     3. Attitude Sensor: (AKA: Artificial Horizon
 * Indicator) Simulate aircraft’s orientation data, including pitch (nose
 * up/down), roll (wing up/down), and yaw (nose left/right). Data format:
 * degrees from the horizon for pitch and roll, magnetic heading for yaw. Update
 * frequency: every 500 milliseconds. 
 *     4. Engine Parameters: Simulate engine
 * thrust and fuel flow. Data format: thrust in pounds-force (lbf). Update
 * frequency: every second. Note: we could also consider issues such as
 * temperature or fuel flow, but we will ignore these for this project due to
 * time issues.
 * 
 * 3.3 Control Signal Specifications 
 *     1. Autopilot Control Frequency: The system
 * should send control signals to the aircraft’s control surfaces (elevators,
 * ailerons, rudders) and engine control systems at least every 500
 * milliseconds. 
 *     2. Execution Check Parameters: After sending a control signal,
 * the system must verify the execution by reading back the relevant sensor data
 * within 200 milliseconds. Success Criteria: A control signal is considered
 * successfully executed if the sensor data reflects the expected change within
 * a margin of error of ±2% for the control surfaces and ±5% for engine
 * parameters, within 1 second of command issuance. Failure Handling: If the
 * execution check fails, the system must attempt to resend the command up to
 * three times before alerting the pilot to the issue via the user interface.
 */


//record to store a data value and whether it is valid
record DataPiece(Double value, Boolean isValidData) {
	public String toString() { return value + " [valid data? " + isValidData + "]"; }
}

//record to store the data used to test 3.2.3
record AttitudeSensor(DataPiece pitch, DataPiece roll, DataPiece yaw) {
	public String toString() { return pitch + ", " + roll + ", " + yaw; }
}

public class TestData {
	
	/**
	 * suppliers of good data
	 */
	static Supplier<DataPiece> speed = () -> generateGoodData(0.0, 1000.0); // 3.2.1
    static Supplier<DataPiece> thrust = () -> generateGoodData(0.0, 500.0); // 3.2.4
    static Supplier<DataPiece> altitude = () -> generateGoodData(0.0, 30000.0); // 3.2.2
    static Supplier<DataPiece> latitude = () -> generateGoodData(-90.0, 90.0); // 3.2.2
    static Supplier<DataPiece> longitude = () -> generateGoodData(-180.0, 180.0); // 3.2.2
    static Supplier<AttitudeSensor> attitudeSensor = () -> generateAttitudeSensor(true); // 3.2.3

	/**
	 * suppliers of bad data
	 */
    static Supplier<DataPiece> badSpeed = () -> generateBadData(0.0, 1000.0); // 3.2.1
    static Supplier<DataPiece> badThrust = () -> generateBadData(0.0, 500.0); // 3.2.4
    static Supplier<DataPiece> badAltitude = () -> generateBadData(0.0, 50000.0); // 3.2.2
    static Supplier<DataPiece> badLatitude = () -> generateBadData(-90.0, 90.0); // 3.2.2
    static Supplier<DataPiece> badLongitude = () -> generateBadData(-180.0, 180.0); // 3.2.2
    static Supplier<AttitudeSensor> badAttitudeSensor = () -> generateAttitudeSensor(false); // 3.2.3

	/**
	 * Method to generate potentially bad data for the attitude sensors.
	 * 
	 * @return a new attitude sensor
	 */
    static AttitudeSensor generateAttitudeSensor(boolean goodData) {
    	if (goodData) {
    		return new AttitudeSensor(generateGoodData(-30.0, 30.0), generateGoodData(-60.0, 60.0),
    				generateGoodData(-180.0, 180.0));
    	}
        return new AttitudeSensor(generateBadData(-30.0, 30.0), generateBadData(-60.0, 60.0),
                generateBadData(-180.0, 180.0));
	}
    
    static int counter = 0;
    
    /**
     * Given lower and upper bounds generate a random value within the
     * bounds that is considered to be 'good'
     * 
     * @param lowerBound
     * @param upperBound
     * @return, a new piece of data that is 'good'
     */
    static DataPiece generateGoodData(Double lowerBound, Double upperBound) {
        assert lowerBound != null;
        assert upperBound != null;
        
        Double randFactor = Math.random();
        if (randFactor == 0.0) {
        	randFactor += 0.0001;
        }
        
        Double value = lowerBound + randFactor * (upperBound - lowerBound);
        return new DataPiece(value, true);
    }

	/**
	 * This method generates a 'bad' piece of data within a given range.
	 * For example, we know the airspeed can never be less than 0 and flights 
	 * must be cancelled if its faster than 100mph. The intention of this 
	 * method is to generate data that is always invalid but never the same 
	 * combination of invalid data.
	 * 
	 * @param lowerBound, the lowest acceptable/valid value
	 * @param upperBound, the maximum acceptable/valid value
	 * @return a new data piece
	 */
    static DataPiece generateBadData(Double lowerBound, Double upperBound) {
        assert lowerBound != null;
        assert upperBound != null;

        Integer salt = (int) (Math.random() * 2) + 1;
        Double value = ++counter % salt == 0 ? upperBound + salt : lowerBound - salt;
        return new DataPiece(value, false);
    }
    
    /**
     * @param sensor, a sensor string
     * @return a mutated 'bad' version of the sensor
     */
    static String generateBadSensor(String sensor) {
    	assert sensor != null;
    	return sensor += "!";
    }

}
