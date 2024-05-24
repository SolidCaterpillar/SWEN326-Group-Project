
public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sensor s1 = new Sensor("s1", 100, 300, 50);
		Sensor s2 = new Sensor("s2", 100, 300, 50);
		Sensor s3 = new Sensor("s3", 100, 300, 50);
		
		System.out.println(s1.getValue());
		System.out.println(s2.getValue());
		System.out.println(s3.getValue());
		System.out.println(s1.getValue());
		System.out.println(s2.getValue());
		
		FCSConnection socket = new FCSConnection("localhost", 1261);
		String message = socket.sendMessage("Hello server!");
		System.out.println(message);
	}

}
