package test;

public class TestHelper {
	
	/**
	 * Generate either a good of bad piece of sensor data depending upon 
	 * the value of the good parameter
	 * 
	 * @param sensor
	 * @param good
	 * @return the new piece of data
	 */
	static DataPiece getDataForSensor(String sensor, boolean good) {
		switch(sensor){
		case "SPEED": 
			return good ? TestData.speed.get() : TestData.badSpeed.get();
		case "THRUST": 
			return good ? TestData.thrust.get() : TestData.badThrust.get();
		case "ALTITUDE": 
			return good ? TestData.altitude.get() : TestData.badAltitude.get();
		case "LATITUDE": 
			return good ? TestData.latitude.get() : TestData.badLatitude.get();
		case "LONGITUDE": 
			return good ? TestData.longitude.get() : TestData.badLongitude.get();
		default: 
			return null;
		}
	}
	
	/**
	 * Given an attitude sensor, retrieve the either the pitch, yaw, of roll of
	 * the sensor
	 * 
	 * @param as
	 * @param sensor
	 * @return the desired piece of data
	 */
	static DataPiece getDataFromAttitudeSensor(AttitudeSensor as, String sensor) {
		switch(sensor){
		case "YAW": 
			return as.yaw();
		case "PITCH": 
			return as.pitch();
		case "ROLL": 
			return as.roll();
		default: 
			return null;
		}
	}

	/**
	 * A return code response from the flight controller is in the form "SENSOR?RETURN_CODE"
	 * Split the string an return the integer value that represents the return code
	 *  
	 * @param resp, the return code from flight controller
	 * @return the return code
	 */
	static int getRetCode(String resp) {
		assert resp != null;
		String[] arr = resp.split("\\?");
		assert arr.length == 2;
	    return Integer.valueOf(arr[1]);
	}
	/**
	 * Given a response from the flight controller of the form:
	 * 'OLD_SENSOR_VALUE=NEW_SENSOR_VALUE'
	 * extract the old data from index 0, and new data from index 1
	 * 
	 * @param resp, flight controller response
	 * @param idx. the index of old or new data
	 * @return
	 */
	static double getOldOrNewValue(String resp, int idx) {
	    assert resp != null;
	    assert idx == 0 || idx == 1;
		String[] arr = resp.split("=");
		assert arr.length == 2;
		return Double.valueOf(arr[idx]);
	}
}
