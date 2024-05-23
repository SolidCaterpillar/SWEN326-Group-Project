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
		
		while(this.simulatorRunning) {
			try {
				recieved = this.in.readLine();
				toReturn = ("Recieved message: " + recieved); //$NON-NLS-1$
				if(recieved.equals("Hello server!")) { //$NON-NLS-1$
					toReturn = "Hello client!"; //$NON-NLS-1$
				}
				this.out.println(toReturn);
				
			} catch(IOException e) {
				close();
				e.printStackTrace();
			}
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
