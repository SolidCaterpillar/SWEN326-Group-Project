package fcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FlightController {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public static void main(String[] args) {
    	FlightController server = new FlightController();
    	server.start(1234);
        System.out.println("Hello world!"); //$NON-NLS-1$
    }

    public void start(int port) {
        try {
		    this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
		    e.printStackTrace();
		}
    	
        try {
		    this.clientSocket = this.serverSocket.accept();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
        try {
			this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void stop() {
         try {
			this.in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
         this.out.close();
    }
}
