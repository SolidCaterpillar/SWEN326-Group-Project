package ui;

import java.net.*;
import java.io.*;

public class UI {
	public static void main(String[] args) {
		// Frame
		FlightControlFrame frame = new FlightControlFrame(); 
		FCSConnection socket = new FCSConnection("localhost", 1300);
		String message = socket.sendMessage("Hello server!");
		System.out.println(message);
		
		message = socket.sendMessage("Test multiple messages!");
		System.out.println(message);
	}
	
	
}

