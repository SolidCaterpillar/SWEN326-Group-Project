# Running the program
The program is envoked by the flight control system that waits for socket connections from the simulation, test, and user interface modules.

1. Run FlightController.java from the SWEN326-Project-FCS directory, this should print "Server starting..." to the standard output.
2. FlightController will listen for a connection on port 1261. To connect to this port you must run the Simulator.java file which will begin transmitting data, this file is found in the SWEN326-Project-Simulator directory.
3. Next, FlightController will listen on port 1262 for the testing module. Hence, you must envoke the Tester.java from the SWEN326-Project-Tester directory next.
4. Lastly, FlightController will listen for a connetion on port 1263 for the UI module. Run the UI.java file from the SWEN326-Project-UI directory to establish the connection.

