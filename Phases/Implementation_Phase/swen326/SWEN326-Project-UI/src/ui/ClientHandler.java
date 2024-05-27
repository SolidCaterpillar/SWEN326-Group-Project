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
		String received;
		try {
			while(this.simulatorRunning) {
				received = this.in.readLine();    // get input 
				String[] data = received.split("="); //$NON-NLS-1$
				
				assert data.length == 2;
				
				// get the data from input string
				String sensorType = data[0];
				double value = this.stringToDouble(data[1]);
				
				// update the state of the flight
				this.fcsconnection.updateUI(sensorType, value);

		// String recieved;
		// String toReturn;
		// // FlightController.ReturnCode returnCode = FlightController.ReturnCode.STABLE;
		
		// try {
		// 	while(this.simulatorRunning) {
		// 		recieved = this.in.readLine();
		// 		//Handle received data, case switch statement perhaps to do different functions based on what is requested?
		// 		toReturn = "Recieved message:" + recieved;
		// 		if(recieved.equals("Hello server!")) { //$NON-NLS-1$
		// 			toReturn = "Hello client!";
		// 		}
		// 		this.out.println(toReturn);
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.close();
			return;
		}
	}
	
	/**
	 * Given input 'SENSOR=..." return the value
	 * @param recv, the input
	 * @return the input value
	 */
	private double stringToDouble(String value) {
    	if (value.charAt(0) == '-') {
    		String newVal = value.substring(1, value.length());
    		return Double.parseDouble(newVal) * -1;
    	}
    	return Double.parseDouble(value);
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
