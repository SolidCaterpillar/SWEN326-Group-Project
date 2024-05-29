package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
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

	// Panels
	private static final long serialVersionUID = 1L;
	private JPanel mapPanel;
	private JPanel flightManagementPanel;
    private JPanel autopilotControlPanel;
    private JPanel sensorDataDisplayPanel;
    private JPanel hazardAlertsPanel;
    
    // Dimensions
    private int height = 600;
    private int width = 1200;
    private int mapHeight  = (int) ((3.0 / 4.0) * height - 35);
    private int mapWidth  = (int) ((8.0 / 14.0) * width);
    
    // Text Fields on the Flight Management Panel
    private JTextField wayPointTextField;
    private JTextField latitudeTextField;
    private JTextField longitudeTextField;
    private JTextField altitudeTextField;
    private JTextField speedTextField;
    private JTextField timeTextField;
    private boolean paintRoute = false;
    
    // AutoPilot Panel
    private JCheckBox autopilotEngagedButton;
    private double faultyAutoPilotChance = 0.2;
    private JLabel autopilotLight;
    private JSlider altitudeSlider;
    private JSlider speedSlider;
    private JSlider headingSlider;
    private int autopilotState;
    
    // Sensor Display Panel
    private JLabel airspeed = new JLabel();
    private JLabel altitude = new JLabel();
    private JLabel pitch = new JLabel();
    private JLabel roll = new JLabel();
    private JLabel yaw = new JLabel();
    private JLabel engine = new JLabel();
    private JLabel latitude = new JLabel();
    private JLabel longitude = new JLabel();
    
    // Hazard Panel
    private JLabel warning = new JLabel();
    private JLabel mitigation = new JLabel();
    private JLabel actionPlan = new JLabel();
    
    // Images
    private ImageIcon flightIcon;
    private ImageIcon map;
    private ImageIcon autopilotOn;
    private ImageIcon autopilotOff;
    private ImageIcon autopilotFaulty;
    
    private Border border;
    private ArrayList<WayPoint> waypoints = new ArrayList<>(); 

    
    /**
     * Constructor for the JFrame
     */
    public FlightControlFrame() {

        this.flightIcon = new ImageIcon("src/ui/Images/flight.png");
        this.map = new ImageIcon(new ImageIcon("src/ui/Images/map.png").getImage().getScaledInstance(this.mapWidth, this.mapHeight, Image.SCALE_DEFAULT));
        this.autopilotOff = new ImageIcon(new ImageIcon("src/ui/Images/off_light.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.autopilotOn = new ImageIcon(new ImageIcon("src/ui/Images/green_light.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        this.autopilotFaulty = new ImageIcon(new ImageIcon("src/ui/Images/red_light.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        
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

    
    /**
     * Adds the panels to the JFrame
     */
    private void addPanels() {
        this.border = BorderFactory.createLineBorder(Color.WHITE, 3);

        this.flightManagementPanel = this.getFlightManagementPanel(border);
        this.mapPanel = this.getMapPanel(border);
        this.autopilotControlPanel = this.getAutopilotControlPanel(border);
        this.sensorDataDisplayPanel = this.getSensorDataDisplayPanel(border);
        this.hazardAlertsPanel = this.getHazardAlertsPanel(border);
        
        this.add(mapPanel);
        this.add(flightManagementPanel);
        this.add(autopilotControlPanel);
        this.add(sensorDataDisplayPanel);
        this.add(hazardAlertsPanel);
    }
    
    
    /**
     * Return Flight Management Panel with input fields to add a way spoint
     * @param border
     * @return The Flight Management Panel
     */
    private JPanel getFlightManagementPanel(Border border) {
    	
    	// Panel
    	flightManagementPanel = new JPanel();
        flightManagementPanel.setBounds(0, 0, (int) ((3.0 / 14.0) * width), (int) ((3.0 / 4.0) * height) - 35);
        flightManagementPanel.setBackground(Color.DARK_GRAY);
        flightManagementPanel.setBorder(border);
        
        // Components
        JLabel wayPointLabel = new JLabel("WayPoint Name:");
        wayPointLabel.setForeground(Color.WHITE);
        wayPointTextField = new JTextField(10);
        JLabel latitudeLabel = new JLabel("Enter Latitude:");
        latitudeLabel.setForeground(Color.WHITE);
        latitudeTextField = new JTextField(10);
        JLabel longitudeLabel = new JLabel("Enter Longitude:");
        longitudeLabel.setForeground(Color.WHITE);
        longitudeTextField = new JTextField(10);
        JLabel altitudeLabel = new JLabel("Enter Altitude:");
        altitudeLabel.setForeground(Color.WHITE);
        altitudeTextField = new JTextField(10);
        JLabel speedLabel = new JLabel("Speed Restriction:");
        speedLabel.setForeground(Color.WHITE);
        speedTextField = new JTextField(10);
        JLabel timeLabel = new JLabel("Arrival Time:");
        timeLabel.setForeground(Color.WHITE);
        timeTextField = new JTextField(10);
        JButton positionSubmitButton = new JButton("Submit");
        positionSubmitButton.addActionListener(e -> {
        		try {
        			String name = this.wayPointTextField.getText();
	        		double latitude = Double.parseDouble(this.latitudeTextField.getText());
	    			double longitude = Double.parseDouble(this.longitudeTextField.getText());
	    			double altitude = Double.parseDouble(this.altitudeTextField.getText());
	    			double speed = Double.parseDouble(this.speedTextField.getText());
	    			String time = this.timeTextField.getText();
	    			
	    			if (latitude > 90 || latitude < -90) throw new NumberFormatException();
	    			if (longitude > 180 || longitude < -180) throw new NumberFormatException();
	    			
	    			addWayPoint(name, latitude, longitude, altitude, speed, time);
	    			
        		} catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(this, "Invalid value or format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
    			mapPanel.repaint();
    			});
        
        JButton createPlanButton = new JButton("Create Plan");
        createPlanButton.addActionListener(e -> {
        	paintRoute = true;
        	mapPanel.repaint();
        });
        
        
        flightManagementPanel.add(wayPointLabel);
        flightManagementPanel.add(wayPointTextField);
        flightManagementPanel.add(latitudeLabel);
        flightManagementPanel.add(latitudeTextField);
        flightManagementPanel.add(longitudeLabel);
        flightManagementPanel.add(longitudeTextField);
        flightManagementPanel.add(altitudeLabel);
        flightManagementPanel.add(altitudeTextField);
        flightManagementPanel.add(speedLabel);
        flightManagementPanel.add(speedTextField);
        flightManagementPanel.add(timeLabel);
        flightManagementPanel.add(timeTextField);
        flightManagementPanel.add(positionSubmitButton);
        flightManagementPanel.add(createPlanButton);
        
        return flightManagementPanel;
        
    }
    
    
    /**
     * Add a waypoint to the list of waypoints using coordinates
     * @param name
     * @param latitude
     * @param longitude
     * @param altitude
     * @param speed
     * @param time
     */
    private void addWayPoint(String name, double latitude, double longitude, double altitude, double speed, String time) {
    	Coordinate c1 = new Coordinate(latitude, longitude, altitude);
		waypoints.add(new WayPoint(name, c1, speed, time));
    }
    
    
    /**
     * Get map panel where the map and way-points will be drawn
     * @param border
     * @return Return map panel
     */
    private JPanel getMapPanel(Border border) {
    	
    	this.mapPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMap(g);
                paintRoute = false;
            }
        };
    	this.mapPanel.setBounds((int) ((3.0 / 14.0) * width), 0, this.mapWidth, this.mapHeight);
    	this.mapPanel.setBorder(border);
        return mapPanel;
        
    }
    
    
    /**
     * Draw way-points on the map
     * @param g Graphics 2D
     */
    protected void drawMap(Graphics g) {
    	g.drawImage(map.getImage(), 0, 0, null);
    	int i = 0;
    	for (WayPoint wp : waypoints) {
    		int x = wp.coordinate().getX(mapWidth) - 5/2;
    		int y = wp.coordinate().getY(mapHeight) - 5/2;
    		g.setColor(wp.coordinate().getColor());
    		g.fillOval(x, y, 5, 5);
            g.drawString(wp.name(), x, y);
            g.setColor(Color.GREEN);
            if (paintRoute) {
            	if (i >= 1) {
            		Graphics2D g2d = (Graphics2D) g;
            		g2d.setStroke(new BasicStroke(2));
            		g2d.drawLine(x + 5/2, y + 5/2, waypoints.get(i-1).coordinate().getX(mapWidth), waypoints.get(i-1).coordinate().getY(mapHeight));
            	}
            }
            i++;
        }
	}

    
    /**
     * Get Autopilot Control Panel
     * @param border
     * @return the panel
     */
	private JPanel getAutopilotControlPanel(Border border) {
    	
    	// Panel
    	autopilotControlPanel = new JPanel();
        autopilotControlPanel.setBounds((int) ((11.0 / 14.0) * width), 0, (int) ((3.0 / 14.0) * width), (int) ((3.0 / 4.0) * height) - 35);
        autopilotControlPanel.setBackground(Color.DARK_GRAY);
        autopilotControlPanel.setBorder(border);
        
        // Components
        autopilotEngagedButton = new JCheckBox("  Autopilot Engaged   ");
        autopilotEngagedButton.addItemListener(e -> {
        	if (e.getStateChange() == ItemEvent.SELECTED) {
        		if (Math.random() >= faultyAutoPilotChance) {
        			this.autopilotLight.setIcon(this.autopilotOn);
        			autopilotState = 1;
        		} else {
        			this.autopilotLight.setIcon(this.autopilotFaulty);
        			autopilotState = -1;
        		}
        	} else {
        		this.autopilotLight.setIcon(this.autopilotOff);
        		autopilotState =0;
        	}
        });
        autopilotLight = new JLabel(this.autopilotOff);
        
        altitudeSlider = new JSlider(1, 0, 13000, 10000);
        speedSlider = new JSlider(1, 0, 860, 700);
        headingSlider = new JSlider(1, 0, 360, 0);
        
        
        autopilotControlPanel.add(autopilotEngagedButton);
        autopilotControlPanel.add(autopilotLight);
        autopilotControlPanel.add(altitudeSlider);
        autopilotControlPanel.add(speedSlider);
        autopilotControlPanel.add(headingSlider);
        return autopilotControlPanel;
        
    }
    
	public int getAutopilotState() {
		return autopilotState;
	}
	
	/**
	 * Get the sensor Data Display Panel
	 * @param border
	 * @return the panel with a 3 by 3 layout for each
	 */
    private JPanel getSensorDataDisplayPanel(Border border) {
    	
    	sensorDataDisplayPanel = new JPanel();
        sensorDataDisplayPanel.setBounds(0, (int) ((3.0 / 4.0) * height) - 35, width / 2, height / 4 + 35 - 20);
        sensorDataDisplayPanel.setBackground(Color.DARK_GRAY);
        sensorDataDisplayPanel.setBorder(border);
        sensorDataDisplayPanel.setLayout(new GridLayout(3,0));
        
        
        latitude.setText("Latitude: -");
        this.modifyLabel(latitude);
        sensorDataDisplayPanel.add(latitude);
        
        longitude.setText("Longitude: -");
        this.modifyLabel(longitude);
        sensorDataDisplayPanel.add(longitude);
        
        altitude.setText("Altitude: -");
        this.modifyLabel(altitude);
        sensorDataDisplayPanel.add(altitude);
        
        airspeed.setText("Airspeed: -");
        this.modifyLabel(airspeed);
        sensorDataDisplayPanel.add(airspeed);
        
        engine.setText("Thrust: -");
        this.modifyLabel(engine);
        sensorDataDisplayPanel.add(engine);
        
        pitch.setText("Pitch: -");
        this.modifyLabel(pitch);
        sensorDataDisplayPanel.add(pitch);
        
        roll.setText("Roll: -");
        this.modifyLabel(roll);
        sensorDataDisplayPanel.add(roll);
        
        yaw.setText("Yaw: -");
        this.modifyLabel(yaw);
        sensorDataDisplayPanel.add(yaw);
       
        return sensorDataDisplayPanel;
    	
    }
    
    
    /**
     * Update the Sensor JLabel to display the new updated value
     * @param sensor the specific JLabel to be updated
     * @param value to which the JLabel is updated
     */
    public void updateSensorDataDisplayPanel(String sensor, String value) {
    	
    	switch (sensor) {
	        case "SPEED": this.airspeed.setForeground(Color.BLUE);
	        			  this.airspeed.setText("AirSpeed : " + value);
	        			  this.airspeed.setForeground(Color.WHITE);
	        			  break;
	        case "THRUST": this.engine.setForeground(Color.BLUE);
	        			   this.engine.setText("Thrust : " + value);
	        			   this.engine.setForeground(Color.WHITE);
	        			   break;
	        case "ALTITUDE":this.altitude.setForeground(Color.BLUE); 
	        				this.altitude.setText("Altitude : " + value);
	        				this.altitude.setForeground(Color.WHITE);
	        			     break;
	        case "LATITUDE":this.latitude.setForeground(Color.BLUE); 
	        				this.latitude.setText("Latitude : " + value);
	        				this.latitude.setForeground(Color.WHITE);
	        			     break;
	        case "LONGITUDE": this.longitude.setForeground(Color.BLUE);
	        				  this.longitude.setText("Longitude : " + value);
	        				  this.longitude.setForeground(Color.WHITE);
	        			      break;
	        case "YAW": this.yaw.setForeground(Color.BLUE);
	        			this.yaw.setText("Yaw : " + value);
	        			this.yaw.setForeground(Color.WHITE);
	        			 break;
	        case "PITCH": this.pitch.setForeground(Color.BLUE);
	        			  this.pitch.setText("Pitch : " + value);
	        			  this.pitch.setForeground(Color.WHITE);
	        			  break;
	        case "ROLL": this.roll.setForeground(Color.BLUE);
	        			 this.roll.setText("Roll : " + value);
	        			 this.roll.setForeground(Color.WHITE);
	        			 break;
    	}
    }
    
    
    /**
     * Return the Hazards Panel which indicates the different hazards
     * @param border
     * @return
     */
    private JPanel getHazardAlertsPanel(Border border) {
    	
    	hazardAlertsPanel = new JPanel();
        hazardAlertsPanel.setBounds(width / 2, (int) ((3.0 / 4.0) * height) - 35, width / 2, height / 4 + 35);
        hazardAlertsPanel.setBackground(Color.DARK_GRAY);
        hazardAlertsPanel.setBorder(border);
        hazardAlertsPanel.setLayout(new GridLayout(1,0));
        
        warning.setText("Warnings: ");
        this.modifyLabel(warning);
        warning.setBackground(Color.RED);
        warning.setOpaque(true);
        hazardAlertsPanel.add(warning);
        
        mitigation.setText("Mitigations: ");
        this.modifyLabel(mitigation);
        mitigation.setBackground(Color.ORANGE);
        mitigation.setForeground(Color.BLACK);
        mitigation.setOpaque(true);
        hazardAlertsPanel.add(mitigation);
        
        actionPlan.setText("Action Plan:");
        this.modifyLabel(actionPlan);
        actionPlan.setBackground(Color.YELLOW);
        actionPlan.setForeground(Color.BLACK);
        actionPlan.setOpaque(true);
        hazardAlertsPanel.add(actionPlan);
        
        return hazardAlertsPanel;
    	
    }

    
    /**
     * Formats a JLabel according to the UI theme
     * @param label the JLabel to be modified
     */
    private void modifyLabel(JLabel label) {
    	label.setForeground(Color.WHITE);
    	label.setBorder(border);
    	label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
}

