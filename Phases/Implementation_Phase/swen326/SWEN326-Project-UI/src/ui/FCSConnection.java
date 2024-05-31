package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class FCSConnection {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private FlightControlFrame frame;
	public FCSConnection(String ip, int port, FlightControlFrame frame) {
		this.frame = frame;
		try {
			clientSocket = new Socket(ip, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			Thread t = new ClientHandler(this, clientSocket, in, out);
			t.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getAutopilotStatus() {
		return frame.getAutopilotState();
	}
	
	public int getThrustSlider() {
		return frame.getThrustSliderValue();
	}
	
	public int getAltitudeSlider() {
		return frame.getAltitudeSliderValue();
	}
	
	public void updateUI(String sensor, String value) {
		switch (sensor) {
    	case "SPEED": this.frame.updateSensorDataDisplayPanel("SPEED", value); break;
    	case "THRUST": this.frame.updateSensorDataDisplayPanel("THRUST", value); break;
    	case "ALTITUDE": this.frame.updateSensorDataDisplayPanel("ALTITUDE", value); break;
    	case "LATITUDE": this.frame.updateSensorDataDisplayPanel("LATITUDE", value); break;
    	case "LONGITUDE": this.frame.updateSensorDataDisplayPanel("LONGITUDE", value); break;
    	case "YAW": this.frame.updateSensorDataDisplayPanel("YAW", value); break;
    	case "PITCH": this.frame.updateSensorDataDisplayPanel("PITCH", value); break;
    	case "ROLL": this.frame.updateSensorDataDisplayPanel("ROLL", value); break;
		}
	}
	public String sendMessage(String msg) {
		out.println(msg);
		String resp = null;
		try {
			resp = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	public void stop() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
