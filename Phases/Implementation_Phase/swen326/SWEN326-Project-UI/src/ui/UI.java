package ui;

import java.net.*;
import java.io.*;

public class UI {
	
	
	public static void main(String[] args) {
		// Frame
		FlightControlFrame frame = new FlightControlFrame(); 
		FCSConnection socket = new FCSConnection("localhost", 1261);
		socket.sendMessage("Test!");
	}
	
	
}

