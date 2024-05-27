package fcs;

import java.io.*;
import java.net.*;

public class FlightController {
    	
    enum PilotState { 
        PILOT_CONTOL,
        AUTO_PILOT
    }
    
    enum DangerState { 
        NORMAL,
        PILOT_ACTION_NEEDED,
        IMMEDIATE_PILOT_ACTION_NEEDED
    }
    
    private Aircraft aircraft;
    private Aircraft testAircraft;
    
    static PilotState currentPilotState = PilotState.PILOT_CONTOL;
    static DangerState currentDangerSate = DangerState.NORMAL;   
        
    public static void main(String[] args) {
        FlightController server = new FlightController();
        System.out.println("Server starting..."); //$NON-NLS-1$
        server.start(1261); // Simulator
        server.start(1262); // Tester
        server.start(1263); // UI
    }
    
    public void start(int port) {
    	this.aircraft = new Aircraft(0, 0, 0, 0, 500, 0);
    	this.testAircraft = new Aircraft(0, 0, 0, 0, 500, 0);  // don't change this ones max thrust value please
    	
        try {
        	ServerSocket serverSocket = new ServerSocket(port); 
    		Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            Thread t = new ClientHandler(this, clientSocket, in, out);
            t.start();
            
//            serverSocket.close();
//            clientSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
            
        }
        
    }
    /**
     * 
     * 
     * @param sensor
     * @param value
     * @return a return code, 0 for success, 
     * 			-1 when data is invalid,
     * 			-2 when the sensor is invalid
     */
    public int updateAircraftState(String sensor, double value, boolean isTesting) {
    	//System.out.println(sensor +" RAAs" + value);
    	Aircraft ac = isTesting ? this.testAircraft : this.aircraft;
    	
    	switch (sensor) {
    	case "SPEED": return ac.setSpeed(value); //$NON-NLS-1$
    	case "THRUST": return ac.setThrust(value); //$NON-NLS-1$
    	case "ALTITUDE": return ac.setAltitude(value); //$NON-NLS-1$
    	case "LATITUDE": return ac.setLatitude(value); //$NON-NLS-1$
    	case "LONGITUDE": return ac.setLongitude(value); //$NON-NLS-1$
    	case "YAW": return ac.setYaw(value); //$NON-NLS-1$
    	case "PITCH": return ac.setPitch(value); //$NON-NLS-1$
    	case "ROLL": return ac.setRoll(value); //$NON-NLS-1$
		default: return -2;
    	}
    }
    
//    public void close(ServerSocket s, Socket c, PrintWriter o, BufferedReader b) {
//        try {
//            b.close();
//            c.close();
//            o.close();
//            b.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    public Aircraft getAircraft() {
    	return this.aircraft;
    }
    
    public Aircraft getTestAircraft() {
    	return this.testAircraft;
    }
}
