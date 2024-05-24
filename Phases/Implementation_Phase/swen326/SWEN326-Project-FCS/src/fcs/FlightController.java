package fcs;

import java.io.*;
import java.net.*;

public class FlightController {
    
	static enum ReturnCode {
		STABLE,    // stable, i.e. good data was received
		B_SPEED, B_THRUST, B_ALTITUDE, B_LATITUDE, B_LONGITUDE, B_YAW, B_PITCH, B_ROLL    // codes for bad data received
	}
	
    enum PilotState { 
        PILOT_CONTOL,
        AUTO_PILOT
    }
    
    enum DangerState { 
        NORMAL,
        PILOT_ACTION_NEEDED,
        IMMEDIATE_PILOT_ACTION_NEEDED
    }
    
    static PilotState currentPilotState = PilotState.PILOT_CONTOL;
    static DangerState currentDangerSate = DangerState.NORMAL;   
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
        
    public static void main(String[] args) {
        FlightController server = new FlightController();
        System.out.println("Server starting..."); //$NON-NLS-1$
        server.start(1300);
    }
    
    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.clientSocket = this.serverSocket.accept();
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            
            Thread t = new ClientHandler(this.clientSocket, this.in, this.out);
            t.start();
            
        }catch (IOException e) {
            e.printStackTrace();
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
