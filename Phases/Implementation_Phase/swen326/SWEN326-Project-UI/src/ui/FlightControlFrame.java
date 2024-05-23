package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FlightControlFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private int waypointNo = 2;
	private JLayeredPane mapPanels;
	private JPanel mapPanel;
	private JPanel flightManagementPanel;
    private JPanel autopilotControlPanel;
    private JPanel sensorDataDisplayPanel;
    private JPanel hazardAlertsPanel;
    private int height = 600;
    private int width = 1200;
    private int mapHeight  = (int) ((3.0 / 4.0) * height - 35);
    private int mapWidth  = (int) ((8.0 / 14.0) * width);
    private JTextField latitudeTextField;
    private JTextField longitudeTextField;
    private JTextField altitudeTextField;
    private JLabel autopilotLight;
    private ImageIcon flightIcon;
    private ImageIcon map;
    private JLabel mapLabel;
    private ImageIcon autopilotOn;
    private ImageIcon autopilotOff;
    private ImageIcon autopilotFaulty;
    private Border border;
    private JCheckBox autopilotEngagedButton;
    private double faultyAutoPilotChance = 0.2;
    private ArrayList<Coordinate> coordinates = new ArrayList<>(); // List of Latitude, Longitude, Altitude
    private ArrayList<WayPoint> waypoints = new ArrayList<>(); 

    public FlightControlFrame() {

        this.flightIcon = new ImageIcon("src/ui/Images/flight.png");
        this.map = new ImageIcon(new ImageIcon("src/ui/Images/map.png").getImage().getScaledInstance(this.mapWidth, this.mapHeight, Image.SCALE_DEFAULT));
        this.autopilotOff = new ImageIcon(new ImageIcon("src/ui/Images/off_light.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.autopilotOn = new ImageIcon(new ImageIcon("src/ui/Images/green_light.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.autopilotFaulty = new ImageIcon(new ImageIcon("src/ui/Images/red_light.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        
        this.setTitle("Flight Controller");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setIconImage(flightIcon.getImage());
        this.setSize(width, height);
        this.setLayout(null);
        this.addPanels();
        this.setVisible(true);

    }

    // Add all the panels to the JFrame
    private void addPanels() {
        this.border = BorderFactory.createLineBorder(Color.WHITE, 3);

        this.flightManagementPanel = this.getFlightManagementPanel(border);
        this.mapPanels = this.getMapPanel(border);
        this.autopilotControlPanel = this.getAutopilotControlPanel(border);
        this.sensorDataDisplayPanel = this.getSensorDataDisplayPanel(border);
        this.hazardAlertsPanel = this.getHazardAlertsPanel(border);
        
        this.add(mapPanels);
        this.add(flightManagementPanel);
        this.add(autopilotControlPanel);
        this.add(sensorDataDisplayPanel);
        this.add(hazardAlertsPanel);
    }

    // Add Map and Label to the center Panel
    private void addMap(JPanel panel) {
    	
    	mapLabel = new JLabel("Flight Route Map");
        mapLabel.setIcon(this.map);
        mapLabel.setHorizontalTextPosition(JLabel.CENTER);
        mapLabel.setVerticalTextPosition(JLabel.BOTTOM);
        mapLabel.setOpaque(true);
        mapLabel.setForeground(Color.WHITE);
        mapLabel.setBackground(Color.DARK_GRAY);
        mapLabel.setBorder(border);
        mapLabel.setVerticalAlignment(JLabel.CENTER);
        mapLabel.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(mapLabel);
        
    }
    
    
    private JPanel getFlightManagementPanel(Border border) {
    	
    	// Panel
    	flightManagementPanel = new JPanel();
        flightManagementPanel.setBounds(0, 0, (int) ((3.0 / 14.0) * width), (int) ((3.0 / 4.0) * height));
        flightManagementPanel.setBackground(Color.DARK_GRAY);
        flightManagementPanel.setBorder(border);
        
        // Components
        JLabel latitudeLabel = new JLabel("Enter Latitude:");
        latitudeLabel.setForeground(Color.WHITE);
        latitudeTextField = new JTextField(10);
        JLabel longitudeLabel = new JLabel("Enter Longitude:");
        longitudeLabel.setForeground(Color.WHITE);
        longitudeTextField = new JTextField(10);
        JLabel altitudeLabel = new JLabel("Enter Altitude:");
        altitudeLabel.setForeground(Color.WHITE);
        altitudeTextField = new JTextField(10);
        JButton positionSubmitButton = new JButton("Submit");
        positionSubmitButton.addActionListener(e -> {
        		double latitude = Double.parseDouble(this.latitudeTextField.getText());
    			double longitude = Double.parseDouble(this.longitudeTextField.getText());
    			double altitude = Double.parseDouble(this.altitudeTextField.getText());
    			Coordinate c1 = new Coordinate(latitude, longitude, altitude);
    			this.coordinates.add(c1);
    			WayPoint w1 = new WayPoint(c1, this.mapWidth, this.mapHeight);
    			w1.setOpaque(false);
    			w1.setBounds((int) ((3.0 / 14.0) * width), 0, this.mapWidth, this.mapHeight);
    			this.mapPanels.add(w1, Integer.valueOf(this.waypointNo));
    			this.waypointNo++;
    			});
        
        flightManagementPanel.add(latitudeLabel);
        flightManagementPanel.add(latitudeTextField);
        flightManagementPanel.add(longitudeLabel);
        flightManagementPanel.add(longitudeTextField);
        flightManagementPanel.add(altitudeLabel);
        flightManagementPanel.add(altitudeTextField);
        flightManagementPanel.add(positionSubmitButton);
        
        return flightManagementPanel;
        
    }
    
    private JLayeredPane getMapPanel(Border border) {
    	
    	
    	this.mapPanel = new JPanel(); 
    	this.addMap(mapPanel);
    	this.mapPanel.setBounds(0, 0, this.mapWidth, this.mapHeight + 35);
    	
    	this.mapPanels = new JLayeredPane();
    	this.mapPanels.setBounds((int) ((3.0 / 14.0) * width), 0, this.mapWidth, this.mapHeight + 35);
    	this.mapPanels.setBorder(border);
        this.mapPanels.add(mapPanel, JLayeredPane.DEFAULT_LAYER);
        
        return mapPanels;
        
    }
    
    private JPanel getAutopilotControlPanel(Border border) {
    	
    	// Panel
    	autopilotControlPanel = new JPanel();
        autopilotControlPanel.setBounds((int) ((11.0 / 14.0) * width), 0, (int) ((3.0 / 14.0) * width), (int) ((3.0 / 4.0) * height));
        autopilotControlPanel.setBackground(Color.DARK_GRAY);
        autopilotControlPanel.setBorder(border);
        
        // Components
        autopilotEngagedButton = new JCheckBox("Autopilot Engaged");
        autopilotLight = new JLabel(this.autopilotOff);
        
        autopilotEngagedButton.addItemListener(e -> {
        	if (e.getStateChange() == ItemEvent.SELECTED) {
        		if (Math.random() >= faultyAutoPilotChance) {
        			this.autopilotLight.setIcon(this.autopilotOn);
        		} else {
        			this.autopilotLight.setIcon(this.autopilotFaulty);
        		}
        	} else {
        		this.autopilotLight.setIcon(this.autopilotOff);
        	}
        });
     
        autopilotControlPanel.add(autopilotEngagedButton);
        autopilotControlPanel.add(autopilotLight);
        return autopilotControlPanel;
        
    }
    
    private JPanel getSensorDataDisplayPanel(Border border) {
    	
    	sensorDataDisplayPanel = new JPanel();
        sensorDataDisplayPanel.setBounds(0, (int) ((3.0 / 4.0) * height), width / 2, height / 4);
        sensorDataDisplayPanel.setBackground(Color.DARK_GRAY);
        sensorDataDisplayPanel.setBorder(border);
        return sensorDataDisplayPanel;
    	
    }
    
    private JPanel getHazardAlertsPanel(Border border) {
    	
    	hazardAlertsPanel = new JPanel();
        hazardAlertsPanel.setBounds(width / 2, (int) ((3.0 / 4.0) * height), width / 2, height / 4);
        hazardAlertsPanel.setBackground(Color.DARK_GRAY);
        hazardAlertsPanel.setBorder(border);
        return hazardAlertsPanel;
    	
    }

}
