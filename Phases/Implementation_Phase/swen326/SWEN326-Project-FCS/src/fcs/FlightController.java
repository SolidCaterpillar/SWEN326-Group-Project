package fcs;

import java.io.*;
import java.net.*;

public class FlightController {
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
