package ui;

import javax.swing.*;
import java.awt.*;

public class WayPoint extends JComponent {
	private static final long serialVersionUID = 1L;
	private Coordinate coordinate;
	private int mapWidth;
	private int mapHeight;
	
	
	public WayPoint(Coordinate coordinate, int mapWidth, int mapHeight) {
		super();
		this.coordinate = coordinate;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        System.out.println("x_raw = " + coordinate.getLongitudePosition(mapWidth));
        System.out.println("y_raw= " + coordinate.getLatitudePosition(mapHeight));
        System.out.println("mapWidth/2 = " + (mapWidth / 2));
        System.out.println("mapheight/2= " + (mapHeight / 2));
        
        g2d.fillOval(coordinate.getLongitudePosition(mapWidth) + mapWidth / 2, ((mapHeight / 2) - coordinate.getLatitudePosition(mapHeight)), 5, 5);
        System.out.println("x = " + (coordinate.getLongitudePosition(mapWidth) + mapWidth / 2));
        System.out.println("y = " + ((mapHeight / 2) - coordinate.getLatitudePosition(mapHeight)));
        
    }
}