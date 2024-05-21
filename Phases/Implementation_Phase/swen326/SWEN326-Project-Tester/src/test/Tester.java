package test;

import org.junit.Test;

public class Tester {
	
	static {
		// create socket connection before running the tests
		FCSConnection.establishConnection(1261);
		assert FCSConnection.socket != null 
		        : "[Socket=NULL] Couldn't connect to Flight Controller.";
		assert FCSConnection.socket.isConnected() 
		        : "[Connection=FALSE] Failed to connect to Flight Controller.";
	}
	
	@Test
	public void draftTestBadAirSpeed() {
		// the following is placeholder code
		
		// get a piece of bad data
		DataPiece as = TestData.airSpeed.get();
		
		// send the bad data to the simulation
		FCSConnection.writer.println(as.value());
		
		// 4. read the system response 
		Boolean expectedSystemRsponse = false; // FCSConnection.reader;
		
		// 5. assert that the system correctly recognized and responded to bad data
		assert as.isValidData() == expectedSystemRsponse : "System failed to recognise bad data.";
	}
	
	@Test
	public void draftAirSpeedDataTest() {
		System.out.println("Draft testing bad air speed data:");
		for (int i = 0; i < 5; i++) {
			AttitudeSensor as = TestData.attitudeSensor.get();
			System.out.println(as);
		}
	}
	
}
