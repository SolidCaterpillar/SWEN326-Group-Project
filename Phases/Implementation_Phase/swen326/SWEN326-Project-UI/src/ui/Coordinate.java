package ui;

public record Coordinate(double latitude, double longitude, double altitude) {
	
	public int getLatitudePosition(int mapHeight) {
		return (int) (mapHeight * Math.sin(Math.toRadians(latitude())));
	}
	
	public int getLongitudePosition(int mapWidth) {
		return (int) (mapWidth * Math.toRadians(longitude()) / Math.PI);
	}
	
}
