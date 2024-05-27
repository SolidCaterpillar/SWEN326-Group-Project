package fcs;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;

import javax.sound.midi.Receiver;


public class ClientHandler extends Thread{
	private FlightController flightController;
	private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    
    private boolean simulatorRunning;
    
	public ClientHandler(FlightController fc, Socket s, BufferedReader input, PrintWriter output) {
		this.flightController = fc;
		this.clientSocket = s;
		this.in = input;
		this.out = output;
		
		this.simulatorRunning = true;
	}
	
	@Override
	public void run() {
		String received;
		try {
			while (this.simulatorRunning) {
			    received = this.in.readLine();    // get input 
			    if (received == null) {
			    	continue;
			    }
				String[] data = received.split("="); //$NON-NLS-1$
				
				assert data.length == 3;
				
				String projectType = data[0];
				String sensorType = data[1];
				String number = data[2];
				
				if("UI".equals(projectType)) {
					String toReturn = sensorType + "=" + handleUICode(sensorType);
					this.out.println(toReturn);
					
				} else if("TESTER".equals(projectType)) {
					double value = this.stringToDouble(data[2]);
					int returnCode = this.flightController.updateAircraftState(sensorType, value);
					this.out.println(sensorType+"="+returnCode);
				} else if("SIMULATOR".equals(projectType)) {
					//this.out.println(sensorType);
				}
				
				
				//double value = this.stringToDouble(data[1]);
				
				// update the state of the flight
				
				
				// should this be changed to send a status code back??
				//String retStr = sensorType + "=" + returnCode;  //$NON-NLS-1$
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.close();
			return;
		}
	}
	
	private String handleUICode(String code) {
		String toReturn = "";
		switch(code){
		case "SPEED":
			double speed = this.flightController.getAircraft().getSpeed();
			toReturn = String.valueOf(speed);
		}
		return toReturn;
	}
	/**
	 * Given input 'SENSOR=..." return the value
	 * @param recv, the input
	 * @return the return code of the update
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
