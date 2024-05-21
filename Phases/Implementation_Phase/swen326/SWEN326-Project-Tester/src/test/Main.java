package test;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@SuppressWarnings("unused")
public class Main {

    private static Socket socket;
	private static PrintWriter writer;
    private static BufferedReader reader;

    static void main(String[] args) {
        connect("localhost", 5000);
    }

    static void connect(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);

            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

}