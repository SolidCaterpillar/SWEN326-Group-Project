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

	/*
	 * A series of suppliers that generate potentially bad data. Can be used to feed the simulation
	 * data that will be processed. The result can then be checked to see whether the simulation 
	 * correctly recognizes bad data and how it responds. 
	 * 
	 * Need to finalize what the range of valid data is for each system component.
     */
    static Supplier<DataPiece> speed = () -> generateBadData(0.0, 1000.0); // 3.2.1
    static Supplier<DataPiece> altitude = () -> generateBadData(0.0, 30000.0); // 3.2.2
    static Supplier<DataPiece> thrust = () -> generateBadData(0.0, 5000.0); // 3.2.4
    static Supplier<AttitudeSensor> attitudeSensor = () -> generateBadAttitudeSensor(); // 3.2.3

	/**
	 * Method to generate potentially bad data for the attitude sensors.
	 * 
	 * @return a new attitude sensor
	 */
    static AttitudeSensor generateBadAttitudeSensor() {
        return new AttitudeSensor(
                generateBadData(-30.0, 30.0), 
                generateBadData(-60.0, 60.0),
                generateBadData(-180.0, 180.0)
            );
	}
    
    static int counter = 0;

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

        // Does the severity of the bad data matter?
        Integer salt = (int) (Math.random() * 2) + 1;
        Double value = ++counter % salt == 0 ? upperBound + salt : lowerBound - salt;
        return new DataPiece(value, false);
    }

}
