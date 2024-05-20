package test;

import java.util.stream.Stream;

/**
 * The simulator will be simulating the following characteristics of an aircraft: 
 * 
 * 3.2 Sensor Data Simulation and Frequency
 *    1. Airspeed Sensor: Data format: knots (nautical miles per hour). Update frequency: every second.
 *    2. Altitude Sensor: Data format: feet above mean sea level (AMSL). Update frequency: every 500 milliseconds.
 *    3. Attitude Sensor: (AKA: Artificial Horizon Indicator) Simulate aircraft’s orientation data, including
 *           pitch (nose up/down), roll (wing up/down), and yaw (nose left/right). Data format: degrees from the
 *           horizon for pitch and roll, magnetic heading for yaw. Update frequency: every 500 milliseconds.
 *    4. Engine Parameters: Simulate engine thrust and fuel flow. Data format: thrust in pounds-force (lbf).
 *           Update frequency: every second. Note: we could also consider issues such as temperature or
 *           fuel flow, but we will ignore these for this project due to time issues.
 *           
 * 3.3 Control Signal Specifications
 *    1. Autopilot Control Frequency: The system should send control signals to the aircraft’s control
 *           surfaces (elevators, ailerons, rudders) and engine control systems at least every 500 milliseconds.
 *    2. Execution Check Parameters: After sending a control signal, the system must verify the execution
 *           by reading back the relevant sensor data within 200 milliseconds. Success Criteria: A control signal is
 *           considered successfully executed if the sensor data reflects the expected change within a margin of error
 *           of ±2% for the control surfaces and ±5% for engine parameters, within 1 second of command issuance.
 *           Failure Handling: If the execution check fails, the system must attempt to resend the command up to
 *           three times before alerting the pilot to the issue via the user interface.
 */

public class DataSimulation {
	public static Stream<Double> airSpeed;    // 3.2.1
	public static Stream<Double> altitudeInFeet;    // 3.2.2
	public static Stream<AltitudeSensor> altitudeSensors;
	
	// record to store the data used to test 3.2.3
	record AltitudeSensor(Double pitch, Double roll,Double yaw) {
		AltitudeSensor {
			assert pitch >= -30 && pitch <= 30; 
			assert roll >= -60 && roll <= 60;
			assert yaw >= -180 && yaw <= 180;
		}
	}
}
