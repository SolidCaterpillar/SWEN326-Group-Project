package fcs;

import java.io.*;
import java.net.Socket;

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
				//String number = data[2];
				
				if("UI".equals(projectType)) { //$NON-NLS-1$
					String toReturn = sensorType + "=" + handleUICode(sensorType); //$NON-NLS-1$
					this.out.println(toReturn);
					
				} else if("TESTER".equals(projectType)) { //$NON-NLS-1$
					double value = stringToDouble(data[2]);
					int returnCode = this.flightController.updateAircraftState(sensorType, value, true);
					this.out.println(sensorType+"="+returnCode); //$NON-NLS-1$
				} else if("SIMULATOR".equals(projectType)) { //$NON-NLS-1$
					double value = stringToDouble(data[2]);
					this.flightController.updateAircraftState(sensorType, value, false);
					this.out.println(received);
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
		String toReturn = "";  //$NON-NLS-1$ 
		switch(code){
		case "SPEED": //$NON-NLS-1$
			double speed = this.flightController.getAircraft().getSpeed();
			toReturn = String.valueOf(speed);
			break;
		case "THRUST": //$NON-NLS-1$
			double thrust = this.flightController.getAircraft().getThrust();
			toReturn = String.valueOf(thrust);
			break;
		case "ALTITUDE": //$NON-NLS-1$
			double altitude = this.flightController.getAircraft().getAltitude();
			toReturn = String.valueOf(altitude);
			break;
		case "LATITUDE": //$NON-NLS-1$
			double latitude = this.flightController.getAircraft().getLatitude();
			toReturn = String.valueOf(latitude);
			break;
		case "LONGITUDE": //$NON-NLS-1$
			double longitude = this.flightController.getAircraft().getLongitude();
			toReturn = String.valueOf(longitude);
			break;
		case "YAW": //$NON-NLS-1$
			double yaw = this.flightController.getAircraft().getYaw();
			toReturn = String.valueOf(yaw);
			break;
		case "PITCH": //$NON-NLS-1$
			double pitch = this.flightController.getAircraft().getPitch();
			toReturn = String.valueOf(pitch);
			break;
		case "ROLL": //$NON-NLS-1$
			double roll = this.flightController.getAircraft().getRoll();
			toReturn = String.valueOf(roll);
			break;
		default: 
			toReturn = "";  //$NON-NLS-1$
		}
		
		return toReturn;
	}
	/**
	 * Given input 'SENSOR=..." return the value
	 * @param recv, the input
	 * @return the return code of the update
	 */
	private static double stringToDouble(String value) {
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
