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
    
    static PilotState currentPilotState = PilotState.PILOT_CONTOL;
    static DangerState currentDangerSate = DangerState.NORMAL;   
        
    public static void main(String[] args) {
        FlightController server = new FlightController();
        System.out.println("Server starting..."); //$NON-NLS-1$
        //server.start(1261); // Simulator
        server.start(1262); // Tester
        server.start(1263); // UI
    }
    
    public void start(int port) {
    	this.aircraft = new Aircraft(0, 0, 0, 0, 500, 0);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            Thread t = new ClientHandler(this, clientSocket, in, out);
            t.start();
            
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
    public int updateAircraftState(String sensor, double value) {
    	switch (sensor) {
    	case "SPEED": return this.aircraft.setSpeed(value); //$NON-NLS-1$
    	case "THRUST": return this.aircraft.setThrust(value); //$NON-NLS-1$
    	case "ALTITUDE": return this.aircraft.setAltitude(value); //$NON-NLS-1$
    	case "LATITUDE": return this.aircraft.setLatitude(value); //$NON-NLS-1$
    	case "LONGITUDE": return this.aircraft.setLongitude(value); //$NON-NLS-1$
    	case "YAW": return this.aircraft.setYaw(value); //$NON-NLS-1$
    	case "PITCH": return this.aircraft.setPitch(value); //$NON-NLS-1$
    	case "ROLL": return this.aircraft.setRoll(value); //$NON-NLS-1$
		default: return -2;
    	}
    }
    
    public void close(ServerSocket s, Socket c, PrintWriter o, BufferedReader b) {
        try {
            b.close();
            c.close();
            o.close();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Aircraft getAircraft() {
    	return aircraft;
    }
}
