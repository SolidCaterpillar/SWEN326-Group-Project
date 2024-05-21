package test;

import org.junit.Test;

public class Tester {
	
	@Test
	public void draftTestBadAirSpeed() {
		// the following is placeholder code
		
		// 1. connect to the simulation
		
		// 2. get a piece of bad data
		DataPiece as = TestData.airSpeeds.get();
		
		// 3. send the as.value() to the simulation
		as.value();
		
		// 4. read the system response 
		Boolean expectedSystemRsponse = false;
		
		// 5. assert that the system correctly recognized and responded to bad data
		assert as.isValidData() == expectedSystemRsponse : "System failed to recognise bad data.";
	}
	
	@Test
	public void draftAirSpeedDataTest() {
		System.out.println("Draft testing bad air speed data:");
		for (int i = 0; i < 5; i++) {
			AttitudeSensor as = TestData.attitudeSensors.get();
			System.out.println(as);
		}
	}
	
}
