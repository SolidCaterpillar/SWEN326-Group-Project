package ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FlightControlFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel mapPanel;
    private JPanel flightManagementPanel;
    private JPanel autopilotControlPanel;
    private JPanel sensorDataDisplayPanel;
    private JPanel hazardAlertsPanel;
    private int height = 600;
    private int width = 1200;
    private JLabel autopilotLight;
    private ImageIcon flightIcon;
    private ImageIcon map;
    private ImageIcon autopilotOn;
    private ImageIcon autopilotOff;
    private ImageIcon autopilotFaulty;
    private Border border;
    private JCheckBox autopilotEngagedButton;
    private double faultyAutoPilotChance = 0.2;

    public FlightControlFrame() {

        this.flightIcon = new ImageIcon("src/ui/Images/flight.png");
        this.map = new ImageIcon(new ImageIcon("src/ui/Images/world_map.jpg").getImage().getScaledInstance((int) ((8.0 / 14.0) * width), (int) ((3.0 / 4.0) * height - 35), Image.SCALE_DEFAULT));
        this.autopilotOff = new ImageIcon(new ImageIcon("src/ui/Images/off_light.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.autopilotOn = new ImageIcon(new ImageIcon("src/ui/Images/green_light.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.autopilotFaulty = new ImageIcon(new ImageIcon("src/ui/Images/red_light.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        
        this.setTitle("Flight Controller");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setIconImage(flightIcon.getImage());
        this.setSize(width, height);
        this.setLayout(null); // Use null layout for absolute positioning
        this.addPanels();
        this.setVisible(true);

    }

    // Add all the panels to the JFrame
    public void addPanels() {
        this.border = BorderFactory.createLineBorder(Color.WHITE, 3);

        this.flightManagementPanel = this.getFlightManagementPanel(border);
        this.mapPanel = this.getMapPanel(border);
        this.autopilotControlPanel = this.getAutopilotControlPanel(border);
        this.sensorDataDisplayPanel = this.getSensorDataDisplayPanel(border);
        this.hazardAlertsPanel = this.getHazardAlertsPanel(border);
        
        this.add(flightManagementPanel);
        this.add(mapPanel);
        this.add(autopilotControlPanel);
        this.add(sensorDataDisplayPanel);
        this.add(hazardAlertsPanel);
    }

    // Add Map and Label to the center Panel
    public void addMap(JPanel panel) {
    	
        JLabel label = new JLabel("Flight Route Map");
        label.setIcon(this.map);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setOpaque(true);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.DARK_GRAY);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        
    }
    
    
    public JPanel getFlightManagementPanel(Border border) {
    	
    	// Panel
    	flightManagementPanel = new JPanel();
        flightManagementPanel.setBounds(0, 0, (int) ((3.0 / 14.0) * width), (int) ((3.0 / 4.0) * height));
        flightManagementPanel.setBackground(Color.DARK_GRAY);
        flightManagementPanel.setBorder(border);
        
        // Components
        JTextField latitudeTextField = new JTextField("Enter Latitude");
        JButton latitudeButton = new JButton("Submit Latitude");
        
        JTextField longitudeTextField = new JTextField("Enter Longitude");
        JButton longitudeButton = new JButton("Submit Longitude");
        
        JTextField altitudeTextField = new JTextField("Enter Altitude");
        JButton altitudeButton = new JButton("Submit Altitude");
        
        flightManagementPanel.add(latitudeTextField);
        flightManagementPanel.add(latitudeButton);
        
        flightManagementPanel.add(longitudeTextField);
        flightManagementPanel.add(longitudeButton);
        
        flightManagementPanel.add(altitudeTextField);
        flightManagementPanel.add(altitudeButton);
        
        return flightManagementPanel;
        
    }
    
    public JPanel getMapPanel(Border border) {
    	
    	mapPanel = new JPanel();
        this.addMap(mapPanel);
        mapPanel.setBounds((int) ((3.0 / 14.0) * width), 0, (int) ((8.0 / 14.0) * width), (int) ((3.0 / 4.0) * height));
        mapPanel.setBorder(border);
        return mapPanel;
        
    }
    
    public JPanel getAutopilotControlPanel(Border border) {
    	
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
    
    public JPanel getSensorDataDisplayPanel(Border border) {
    	
    	sensorDataDisplayPanel = new JPanel();
        sensorDataDisplayPanel.setBounds(0, (int) ((3.0 / 4.0) * height), width / 2, height / 4);
        sensorDataDisplayPanel.setBackground(Color.DARK_GRAY);
        sensorDataDisplayPanel.setBorder(border);
        return sensorDataDisplayPanel;
    	
    }
    
    public JPanel getHazardAlertsPanel(Border border) {
    	
    	hazardAlertsPanel = new JPanel();
        hazardAlertsPanel.setBounds(width / 2, (int) ((3.0 / 4.0) * height), width / 2, height / 4);
        hazardAlertsPanel.setBackground(Color.DARK_GRAY);
        hazardAlertsPanel.setBorder(border);
        return hazardAlertsPanel;
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
        
		
	}

}
