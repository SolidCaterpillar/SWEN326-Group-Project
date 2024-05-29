package ui;

import java.io.*;
import java.net.Socket;

import javax.sound.midi.Receiver;


public class ClientHandler extends Thread{
	private FCSConnection fcsconnection;
	private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    
    private boolean simulatorRunning;
    
	public ClientHandler(FCSConnection fc, Socket s, BufferedReader input, PrintWriter output) {
		this.fcsconnection = fc;
		this.clientSocket = s;
		this.in = input;
		this.out = output;
		
		this.simulatorRunning = true;
	}
	
	@Override
	public void run() {
		//try {
			while(this.simulatorRunning) {
				sendRequest("UI=SPEED");
				sendRequest("UI=THRUST");
				sendRequest("UI=ALTITUDE");
				sendRequest("UI=LATITUDE");
				sendRequest("UI=LONGITUDE");
				sendRequest("UI=YAW");
				sendRequest("UI=PITCH");
				sendRequest("UI=ROLL");
				
				sendRequest("UI=AUTOPILOT=" + this.fcsconnection.getAutopilotStatus());
			 
				//message = this.in.readLine();    // get input 
				
				// get the data from input string
		// String recieved;
		// String toReturn;
		// // FlightController.ReturnCode returnCode = FlightController.ReturnCode.STABLE;
		
		// try {
		// 	while(this.simulatorRunning) {
		// 		recieved = this.in.readLine();
		// 		//Handle rethis.speed = 0;ceived data, case switch statement perhaps to do different functions based on what is requested?
		// 		toReturn = "Recieved message:" + recieved;
		// 		if(recieved.equals("Hello server!")) { //$NON-NLS-1$
		// 			toReturn = "Hello client!";
		// 		}
		// 		this.out.println(toReturn);
			}
		//} catch (IOException e) {
		//	e.printStackTrace();
		//	this.close();
		//	return;
		//}
	}

	public void sendRequest(String code) {
		String message = this.fcsconnection.sendMessage(code);
		
		String[] data = message.split("="); //$NON-NLS-1$
		
		String sensorType = data[0];
		if(data.length == 2) {
			String value = data[1];
			this.fcsconnection.updateUI(sensorType, value);
		}
		
		
		
	}
	public void close() {
		try {
			this.clientSocket.close();
			this.in.close();
			this.out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
