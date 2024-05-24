package ui;

import java.awt.Color;

public record Coordinate(double latitude, double longitude, double altitude) {
	
	private static double lambda0 = 0; 
	
	public int getX(int mapWidth){
		double Rx = mapWidth / (2 * Math.PI);
		double lambda = Math.toRadians(this.longitude - lambda0);
		double x = Rx * lambda;
		int mapX = (int) (mapWidth / 2 + x);
		return mapX;
	}
	
	public int getY(int mapHeight){
		double Ry = mapHeight / (Math.PI * Math.sqrt(2.0)) ;
		double phi = Math.toRadians(this.latitude);
		double y = Ry / 2 * Math.log((1 + Math.sin(phi)) / (1 - Math.sin(phi)));
		int mapY = (int) (mapHeight / 2 - y);
		return mapY;
	}
	
	public Color getColor(){
		// Assuming Altitude in meters
		return new Color(Integer.max(0, (int) (255 - this.altitude() / 50)), 0, Integer.min(255, (int) (this.altitude() / 50)));
	}
	
}
