package test;

import java.util.List;

public class TestHelper {
	
	static final List<String> SENSORS = List.of("SPEED", "THRUST", "ALTITUDE", "LATITUDE", "LONGITUDE");
	
	static final List<String> ATTITUDE_SENSORS = List.of("YAW", "PITCH", "ROLL");
	
	/**
	 * Generate either a good of bad piece of sensor data depending upon 
	 * the value of the good parameter
	 * 
	 * @param sensor
	 * @param good
	 * @return the new piece of data
	 */
	public static DataPiece getDataForSensor(String sensor, boolean good) {
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
	public static DataPiece getDataFromAttitudeSensor(AttitudeSensor as, String sensor) {
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
	    return Integer.valueOf(arr[1]);
//	    if (arr[1].charAt(0) == '-') {
//	    	arr[1] = arr[1].substring(1, arr[1].length());
//	    	return Integer.parseInt(arr[1]) * -1;
//	    }
//	    return Integer.parseInt(arr[1]);
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
		String[] arr = resp.split("=");
	    	
//	    if (arr[idx].charAt(0) == '-') {
//	    	arr[idx] = arr[idx].substring(1, arr[idx].length());
//	    	return Double.parseDouble(arr[idx]) * -1;
//	    }
	    return Double.valueOf(arr[idx]);
	}
}
