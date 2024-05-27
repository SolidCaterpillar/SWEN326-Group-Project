import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        
        RedundantSensor<Double> attitudeYaw= new RedundantSensor<>(Arrays.asList(
                new AttitudeYaw(), new AttitudeYaw(), new AttitudeYaw()
            ));
        
        Engine engine = new Engine();
        
        engine.setValue(123);
        engine.simulate();
        System.out.println("fdgnfkdngkfdn"+engine.getValue());
            
		FCSConnection socket = new FCSConnection("localhost", 1261);
		
		
		  Timer timer1 = new Timer();
	      timer1.schedule(new TimerTask() {
	            @Override
	            public void run() {
	            	engine.simulate();
	            	String message = socket.sendMessage("SIMULATOR=SPEED=" + engine.getValue());
	        		System.out.println( "      "+ message);
	            	
	                //System.out.println();
	            }
	            
	        }, 0, 1000);
	 
	        
	 
	        
	    
	}
}
