package ui;

import java.net.*;
import java.io.*;

/**
 * The UI class
 */
public class UI {
	public static void main(String[] args) {
		// The UI Frame
		FlightControlFrame frame = new FlightControlFrame(); 

		// The Connection Socket to FCSConnection
		FCSConnection socket = new FCSConnection("localhost", 1263, frame);
	}
	
}

