package fcs;

import java.io.*;
import java.net.Socket;


public class ClientHandler extends Thread{
	private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    
    private boolean simulatorRunning;
    
	public ClientHandler(Socket s, BufferedReader input, PrintWriter output) {
		this.clientSocket = s;
		this.in = input;
		this.out = output;
		
		this.simulatorRunning = true;
	}
	@Override
	public void run() {
		String recieved;
		String toReturn;
		// FlightController.ReturnCode returnCode = FlightController.ReturnCode.STABLE;
		
		try {
			while(this.simulatorRunning) {
				recieved = this.in.readLine();
				//Handle received data, case switch statement perhaps to do different functions based on what is requested?
				toReturn = "Recieved message:" + recieved;
				if(recieved.equals("Hello server!")) { //$NON-NLS-1$
					toReturn = "Hello client!";
				}
				this.out.println(toReturn);
			}
		} catch (IOException e) {
			e.getMessage();
			this.close();
			return;
		}
	}
	
	public void close() {
		try {
			this.clientSocket.close();
			this.in.close();
			this.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
