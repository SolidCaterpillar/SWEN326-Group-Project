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
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
        
    public static void main(String[] args) {
        FlightController server = new FlightController();
        System.out.println("Server starting..."); //$NON-NLS-1$
        server.start(1261);
    }
    
    public void start(int port) {
    	this.aircraft = new Aircraft(0, 0, 0, 0, 500, 0);
        try {
            this.serverSocket = new ServerSocket(port);
            this.clientSocket = this.serverSocket.accept();
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            
            Thread t = new ClientHandler(this, this.clientSocket, this.in, this.out);
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
    
    public void close() {
        try {
            this.in.close();
            this.out.close();
            this.clientSocket.close();
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
