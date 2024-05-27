package test;

import java.util.List;

public class TestHelper {
	
	static final List<String> SENSORS = List.of("SPEED", "THRUST", "ALTITUDE", "LATITUDE", "LONGITUDE");
	
	static final List<String> ATTITUDE_SENSORS = List.of("YAW", "PITCH", "ROLL");
	
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
	
	public static AttitudeSensor getAttitudeSensor(boolean good) {
		return good ? TestData.attitudeSensor.get() : TestData.badAttitudeSensor.get();
	}

	static int getRetCode(String resp) {
		assert resp != null;
		String[] arr = resp.split("\\?");
	    	
	    if (arr[1].charAt(0) == '-') {
	    	arr[1] = arr[1].substring(1, arr[1].length());
	    	return Integer.parseInt(arr[1]) * -1;
	    }
	    return Integer.parseInt(arr[1]);
	}
	
	static double getOldOrNewValue(String resp, int idx) {
	    assert resp != null;
		String[] arr = resp.split("=");
	    	
	    if (arr[idx].charAt(0) == '-') {
	    	arr[idx] = arr[idx].substring(1, arr[idx].length());
	    	return Double.parseDouble(arr[idx]) * -1;
	    }
	    return Double.parseDouble(arr[idx]);
	}
}
