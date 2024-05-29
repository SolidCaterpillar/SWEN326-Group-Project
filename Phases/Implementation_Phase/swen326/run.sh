#! /bin/bash

javac SWEN326-Project-FCS/src/fcs/Aircraft.java SWEN326-Project-FCS/src/fcs/ClientHandler.java SWEN326-Project-FCS/src/fcs/FlightController.java SWEN326-Project-FCS/src/fcs/package-info.java SWEN326-Project-Simulator/src/AirspeedSensor.java SWEN326-Project-Simulator/src/AltitudeSensor.java SWEN326-Project-Simulator/src/AttitudePitch.java SWEN326-Project-Simulator/src/AttitudeRoll.java SWEN326-Project-Simulator/src/AttitudeYaw.java SWEN326-Project-Simulator/src/Engine.java SWEN326-Project-Simulator/src/FCSConnection.java SWEN326-Project-Simulator/src/RedundantSensor.java SWEN326-Project-Simulator/src/Simulator.java SWEN326-Project-Tester/src/test/FCSConnection.java SWEN326-Project-Tester/src/test/TestData.java SWEN326-Project-Tester/src/test/Tester.java SWEN326-Project-Tester/src/test/TestHelper.java SWEN326-Project-UI/src/ui/ClientHandler.java SWEN326-Project-UI/src/ui/Coordinate.java SWEN326-Project-UI/src/ui/FCSConnection.java SWEN326-Project-UI/src/ui/FlightControlFrame.java SWEN326-Project-UI/src/ui/UI.java SWEN326-Project-UI/src/ui/WayPoint.java

# fcs -> simulator -> tester -> ui

jar cvf junit-platform-console-standalone-1.7.2.jar -C SWEN326-Project-FCS/src .

junit-platform-console-standalone-1.7.2.jar
java SWEN326-Project-FCS/src/fcs/FlightController
java SWEN326-Project-Simulator/src/Simulator

java SWEN326-Project-UI/src/ui/UI
