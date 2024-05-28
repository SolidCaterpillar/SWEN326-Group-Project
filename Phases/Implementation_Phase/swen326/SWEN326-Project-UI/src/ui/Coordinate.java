package ui;

import java.awt.Color;

/**
 * A Coordinate that is made with the user input
 */
public record Coordinate(double latitude, double longitude, double altitude) {
	
	private static double lambda0 = 0; 
	
	/**
	 * Maps the longitude to the corresponding y value
	 * @param mapWidth Width of the map
	 * @return x position on the map
	 */
	public int getX(int mapWidth){
		double Rx = mapWidth / (2 * Math.PI);
		double lambda = Math.toRadians(this.longitude - lambda0);
		double x = Rx * lambda;
		int mapX = (int) (mapWidth / 2 + x);
		return mapX;
	}
	
	/**
	 * Maps the latitude to the corresponding x value
	 * @param mapHeight height of the map
	 * @return y position on the map
	 */
	public int getY(int mapHeight){
		double Ry = mapHeight / (Math.PI * Math.sqrt(2.0)) ;
		double phi = Math.toRadians(this.latitude);
		double y = Ry / 2 * Math.log((1 + Math.sin(phi)) / (1 - Math.sin(phi)));
		int mapY = (int) (mapHeight / 2 - y);
		return mapY;
	}
	
	/**
	 * Determine the color of the coordinate, the more red - the closer to 0, the more blue - the higher the position
	 * @return Color of the coordinate
	 */
	public Color getColor(){
		// Assuming Altitude in meters
		return new Color(Integer.max(0, (int) (255 - this.altitude() / 50)), 0, Integer.min(255, (int) (this.altitude() / 50)));
	}
	
}
