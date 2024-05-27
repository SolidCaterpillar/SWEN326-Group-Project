import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Simulator {
	public static void main(String[] args) {
		List<AirspeedSensor> airspeedList = new ArrayList<AirspeedSensor>(Arrays.asList(
	            new AirspeedSensor(), new AirspeedSensor(), new AirspeedSensor()
	        ));
	        
        List<AltitudeSensor> altitudeList = new ArrayList<AltitudeSensor>(Arrays.asList(
            new AltitudeSensor(), new AltitudeSensor(), new AltitudeSensor()
        	));
        
        List<AttitudePitch> attitudePitchList =  new ArrayList<AttitudePitch> (Arrays.asList(
            new AttitudePitch(), new AttitudePitch(), new AttitudePitch()
        	));
        
        List<AttitudeRoll> attitudeRollList =  new ArrayList<AttitudeRoll> (Arrays.asList(
	            new AttitudeRoll(), new AttitudeRoll(), new AttitudeRoll()
	        ));
        
        List<AttitudeYaw> attitudeYawList =  new ArrayList<AttitudeYaw> (Arrays.asList(
	            new AttitudeYaw(), new AttitudeYaw(), new AttitudeYaw()
        	));
	        
        RedundantSensor<AirspeedSensor> airspeedSensor = new RedundantSensor<>(airspeedList);
        RedundantSensor<AltitudeSensor> altitudeSensor = new RedundantSensor<>(altitudeList);
        RedundantSensor<AttitudePitch> attitudePitch = new RedundantSensor<>(attitudePitchList);
        RedundantSensor<AttitudeRoll> altitudeRoll = new RedundantSensor<>(attitudeRollList);
        RedundantSensor<AttitudeYaw> attitudeYaw = new RedundantSensor<>(attitudeYawList);

        SensorData sensorData = new SensorData(airspeedSensor, altitudeSensor, attitudePitch, altitudeRoll, attitudeYaw, new Engine());
		
		FCSConnection socket = new FCSConnection("localhost", 1261);
		
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String message = socket.sendMessage("SPEED=500.0");
                System.out.println(message);
            }
        }, 0, 1000); 
        
	}

}
