package test;

import org.junit.Test;

public class Tester {
	
	static {
		// create socket connection before running the tests
		FCSConnection.establishConnection(1300);
		assert FCSConnection.socket != null 
		        : "[Socket=NULL] Couldn't connect to Flight Controller.";
		assert FCSConnection.socket.isConnected() 
		        : "[Connection=FALSE] Failed to connect to Flight Controller.";
	}
	
	@Test
	public void test1() {		
		// get a piece of bad data
		DataPiece as = TestData.airSpeed.get();
		
		// send the bad data to the simulation
		String response = FCSConnection.sendMessage("SPEED="+as.value().toString());
		System.out.println("WFT "+response);
		int respData = FCSConnection.getDataFromResponse(response);
		
		// 5. assert that the system correctly recognized and responded to bad data
		assert !as.isValidData() && respData == -1 : "System failed to recognise bad data.";
	}
	
	@Test
	public void test2() {
		System.out.println("Draft testing bad air speed data:");
		for (int i = 0; i < 5; i++) {
			AttitudeSensor as = TestData.attitudeSensor.get();
			System.out.println(as);
		}
	}
	
}
