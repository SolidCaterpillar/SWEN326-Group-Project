package ui;

import java.awt.Color;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

public class FlightControlFrame extends JFrame{
	
	private JPanel mapPanel;
	private JPanel flightManagementPanel;
	private JPanel autopilotControlPanel;
	private JPanel sensorDataDisplayPanel;
	private JPanel hazardAlertsPanel;
	ImageIcon flightIcon;
	ImageIcon map;
	Border border;
	
	public FlightControlFrame(){
		
		this.flightIcon = new ImageIcon(FlightControlFrame.class.getResource("/Images/flight.png"));
		this.setTitle("Flight Controller");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1200,800);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setIconImage(flightIcon.getImage());
		
		this.addPanels();
		//this.pack();
	}
	
	
	// Add all the panels to the JFrame
	public void addPanels(){
		this.border = BorderFactory.createLineBorder(Color.WHITE, 3);
		
		flightManagementPanel = new JPanel();
		flightManagementPanel.setBounds(0, 0, 200, 600);
		
		mapPanel = new JPanel();
		this.addMap(mapPanel);
		mapPanel.setBounds(200, 0, 800, 600);
		
		autopilotControlPanel = new JPanel();
		autopilotControlPanel.setBounds(1000, 0, 200, 600);
		
		sensorDataDisplayPanel = new JPanel();
		sensorDataDisplayPanel.setBounds(0, 600, 600, 200);
		
		hazardAlertsPanel = new JPanel();
		hazardAlertsPanel.setBounds(600, 600, 600, 200);
		
		var panels = List.of(flightManagementPanel, mapPanel, autopilotControlPanel, sensorDataDisplayPanel, hazardAlertsPanel);
		for (int i = 0; i < panels.size(); i++) {
			this.add(panels.get(i));
		}
		
	}
	
	
	// Add Map and Label to the center Panel
	public void addMap(JPanel panel) {
		
		this.map = new ImageIcon(FlightControlFrame.class.getResource("/Images/world_map.jpg"));
		JLabel label = new JLabel("Flight Route Map");
		label.setIcon(this.map);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setForeground(Color.WHITE);
		label.setBorder(border);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label);
		
	}
}
