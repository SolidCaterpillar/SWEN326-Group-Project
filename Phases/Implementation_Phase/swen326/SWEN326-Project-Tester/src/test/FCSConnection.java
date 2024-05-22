package test;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FCSConnection {

    static Socket socket;
	static PrintWriter writer;
    static BufferedReader reader;

    /**
     * Establish a connection using local host with the flight controller.
     * 
     * @param port
     */
    static void establishConnection(int port) {
        try {
        	socket = new Socket("localhost", port);
            
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
   
    static void closeConnection() {
    	try {
    		socket.close();
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    static String sendMessage(Object msg) {
		writer.println(msg);
		String resp = null;
		try {
			resp = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}

}