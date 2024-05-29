package test;

import org.junit.Test;

public class Tester {

	static {
		// create socket connection before running the tests
		FCSConnection.establishConnection(1262);
		assert FCSConnection.socket != null : "[Socket=NULL] Couldn't connect to Flight Controller.";
		assert FCSConnection.socket.isConnected() : "[Connection=FALSE] Failed to connect to Flight Controller.";
	}

	/**
	 * For every aircraft sensor, generate a good piece of sensor data and check 
	 * that the return code of applying the data is the success code (0), and that 
	 * the sensor data is reassigned to equal the good data it was given
	 */
	@Test
	public void test1GoodSensors() {
		for (String sensor : TestHelper.SENSORS) {
			// get a piece of good data
			DataPiece data = TestHelper.getDataForSensor(sensor, true);

			// send the good data to the fcs
			FCSConnection.sendMessage("TESTER=" + sensor + "=" + data.value());
			String sucessResponse = FCSConnection.recvMessage();
			String dataUpdateResponse = FCSConnection.recvMessage();
			
			double newValue = TestHelper.getOldOrNewValue(dataUpdateResponse, 1);
			
			// check return code is good, and new value = data.value()
			assert TestHelper.getRetCode(sucessResponse) == 0 : "Failed to update: "+sensor;
			assert newValue == data.value() : "Failed to update: "+sensor;
		}
	}

	/**
	 * generate a good attitude sensor and check that the return code
	 * of sending the data is the success code, and that the sensor post state
	 * is == to the data it was given
	 */
	@Test
	public void test2GoodAttitudeSensors() {
		// generate a good attitude sensor
		AttitudeSensor as = TestData.generateAttitudeSensor(true);
		for (String sensor : TestHelper.ATTITUDE_SENSORS) {
			// retrieve the data from an attitude sensor
			DataPiece data = TestHelper.getDataFromAttitudeSensor(as, sensor);

			// send the update to the fcs
			FCSConnection.sendMessage("TESTER=" + sensor + "=" + data.value());
			String successResponse = FCSConnection.recvMessage();
			String dataUpdateResponse = FCSConnection.recvMessage();
			
			double newValue = TestHelper.getOldOrNewValue(dataUpdateResponse, 1);

			// check return code is good, and new value = data.value()
			assert TestHelper.getRetCode(successResponse) == 0 : "Failed to update: "+sensor;
			assert newValue == data.value() : "Failed to update: "+sensor;
		}
	}

	/**
	 * for each sensor get some bad data and check that the return code
	 * of sending the data is a bad code, and that the before state
	 * of the sensor is == to the post state
	 */
	@Test
	public void test3BadSensors() {
		for (String sensor : TestHelper.SENSORS) {
			// get a piece of bad data
			DataPiece data = TestHelper.getDataForSensor(sensor, false);

			// send the bad data to the fcs
			FCSConnection.sendMessage("TESTER=" + sensor + "=" + data.value());
			String sucessResponse = FCSConnection.recvMessage();
			String dataUpdateResponse = FCSConnection.recvMessage();

			double before = TestHelper.getOldOrNewValue(dataUpdateResponse, 0);
			double after = TestHelper.getOldOrNewValue(dataUpdateResponse, 1);

			// check return code is bad, and sensor was not updated
			assert TestHelper.getRetCode(sucessResponse) == -1 : "Updated sensor["+sensor+"] with bad data!" ;
			assert before == after : "Updated sensor["+sensor+"] with bad data!";
		}
	}

	/**
	 * generate a bad attitude sensor and check that the return code
	 * of sending the data is a bad code, and that the before state
	 * of the attitude sensor is == to the post state
	 */
	@Test
	public void test4BadAttitudeSensors() {
		// generate a bad attitude sensor
		AttitudeSensor as = TestData.generateAttitudeSensor(false);
		for (String sensor : TestHelper.ATTITUDE_SENSORS) {
			// retrieve the data from an attitude sensor
			DataPiece data = TestHelper.getDataFromAttitudeSensor(as, sensor);

			// send the bad data to the fcs
			FCSConnection.sendMessage("TESTER=" + sensor + "=" + data.value());
			String sucessResponse = FCSConnection.recvMessage();
			String dataUpdateResponse = FCSConnection.recvMessage();

			double before = TestHelper.getOldOrNewValue(dataUpdateResponse, 0);
			double after = TestHelper.getOldOrNewValue(dataUpdateResponse, 1);

			// check return code is bad, and sensor was not updated
			assert TestHelper.getRetCode(sucessResponse) == -1 : "Updated for bad data!";
			assert before == after : "Updated sensor["+sensor+"] with bad data!";
		}
	}

	/**
	 * test that the system correctly recognizes bad sensor names, 
	 * check the return code is bad, and sensor was not updated, 
	 * and that no sensor was ever read
	 */
	@Test
	public void test5BadSensorNames() {
		for (String sensor : TestHelper.SENSORS) {
			DataPiece data = TestHelper.getDataForSensor(sensor, true);
			
			// make a bad sensor name
			String badName = TestData.generateBadSensor(sensor);

			// send the bad data to the fcs
			FCSConnection.sendMessage("TESTER=" + badName + "=" + data.value());
			String sucessResponse = FCSConnection.recvMessage();
			String dataUpdateResponse = FCSConnection.recvMessage();

			double before = TestHelper.getOldOrNewValue(dataUpdateResponse, 0);
			double after = TestHelper.getOldOrNewValue(dataUpdateResponse, 1);

			// check return code is bad, and sensor was not updated, and that no sensor was ever read
			assert TestHelper.getRetCode(sucessResponse) == -2 : "Updated sensor["+sensor+"] with bad name!";
			assert before == after : "Updated sensor["+sensor+"]with bad name!";
			assert before == Double.MIN_VALUE : "incorrect return for bad data";
		}
	}

}
