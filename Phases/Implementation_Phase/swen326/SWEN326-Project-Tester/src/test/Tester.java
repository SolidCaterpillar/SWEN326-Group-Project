package test;

import org.junit.Test;

public class Tester {
	
	static {
		// create socket connection before running the tests
		FCSConnection.establishConnection(1262);
		assert FCSConnection.socket != null 
		        : "[Socket=NULL] Couldn't connect to Flight Controller.";
		assert FCSConnection.socket.isConnected() 
		        : "[Connection=FALSE] Failed to connect to Flight Controller.";
	}
	
	@Test
	public void testGoodSensors() {
		for (String sensor : TestHelper.SENSORS) {
			// get a piece of bad data
			DataPiece data = TestHelper.getDataForSensor(sensor, true);
			
			// send the bad data to the simulation
			FCSConnection.sendMessage("TESTER="+sensor+"="+data.value());
			String sucessResponse = FCSConnection.recvMessage();
			String dataUpdateResponse = FCSConnection.recvMessage();
			
			// check return code is good, and new value = data.value()
			assert TestHelper.getRetCode(sucessResponse) == 0 : "Failed to update.";
			assert TestHelper.getOldOrNewValue(dataUpdateResponse, 1) == data.value() : "Failed to update.";
		}
	}
	
	
}
