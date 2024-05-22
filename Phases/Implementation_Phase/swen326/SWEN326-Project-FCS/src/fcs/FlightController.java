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
        server.recieveData();
        
    }

    //Will need to be changed, for now just checking if socket works
    public void recieveData() {
        String inputLine = ""; //$NON-NLS-1$
        while(!inputLine.equals("Shutdown")) { //$NON-NLS-1$
            try {
                inputLine = this.in.readLine();
                System.out.println("Recieved: " + inputLine); //$NON-NLS-1$
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } finally {
                this.close();
            }
        }
    }
    
    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.clientSocket = this.serverSocket.accept();
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
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
