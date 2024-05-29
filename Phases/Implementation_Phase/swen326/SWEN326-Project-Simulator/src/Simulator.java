import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Simulator {
	public static void main(String[] args) {
	
        RedundantSensor<Double> airspeedSensor = new RedundantSensor<>(Arrays.asList(
                new AirspeedSensor(), new AirspeedSensor(), new AirspeedSensor()
            ));
            
        RedundantSensor<Double> altitudeSensor = new RedundantSensor<>(Arrays.asList(
        		new AltitudeSensor(), new AltitudeSensor(), new AltitudeSensor()
        	));
        
        RedundantSensor<Double> attitudePitch = new RedundantSensor<>(Arrays.asList(
                new AttitudePitch(), new AttitudePitch(), new AttitudePitch()
            ));
        
        RedundantSensor<Double> attitudeRoll = new RedundantSensor<>(Arrays.asList(
                new AttitudeRoll(), new AttitudeRoll(), new AttitudeRoll()
            ));
        
        RedundantSensor<Double> attitudeYaw = new RedundantSensor<>(Arrays.asList(
                new AttitudeYaw(), new AttitudeYaw(), new AttitudeYaw()
            ));
        
        Engine engine = new Engine();
        
        airspeedSensor.setValue(241);
        altitudeSensor.setValue(321);
        attitudePitch.setValue(231);
        attitudeRoll.setValue(125);
        attitudeYaw.setValue(234);
        engine.setValue(123);

            
		FCSConnection socket = new FCSConnection("localhost", 1261);
		
		
		Timer timer1 = new Timer();
	      timer1.schedule(new TimerTask() {
	            @Override
	            public void run() {
	            	altitudeSensor.simulate();
	            	attitudePitch.simulate();
	            	attitudeRoll.simulate();
	            	attitudeYaw.simulate();
	            	String altitude = socket.sendMessage("SIMULATOR=ALTITUDE=" + String.format("%.3f", altitudeSensor.getValue()));
	            	String pitch = socket.sendMessage("SIMULATOR=PITCH=" + String.format("%.3f", attitudePitch.getValue()));
	            	String roll = socket.sendMessage("SIMULATOR=ROLL=" + String.format("%.3f", attitudeRoll.getValue()));
	            	String yaw = socket.sendMessage("SIMULATOR=YAW=" + String.format("%.3f", attitudeYaw.getValue()));
	            	
	            	
	        		//System.out.println("altitude " +altitude);
	        		//System.out.println("pitch " +pitch);
	        		//System.out.println("roll " +roll);
	        		//System.out.println("yaw " +yaw);
	            }
	            
	        }, 0, 500);
	        
		
		
		  Timer timer2 = new Timer();
	      timer2.schedule(new TimerTask() {
	            @Override
	            public void run() {
	            	airspeedSensor.simulate();
	            	engine.simulate();
	            	String airspeed = socket.sendMessage("SIMULATOR=SPEED=" + String.format("%.3f", airspeedSensor.getValue()));
	            	String thrust = socket.sendMessage("SIMULATOR=THRUST=" + String.format("%.3f", airspeedSensor.getValue()));
	        		//System.out.println("airspeed "+ airspeed);
	        		//System.out.println("thrust "+ thrust);
	            }
	            
	        }, 0, 1000);
	}
}
