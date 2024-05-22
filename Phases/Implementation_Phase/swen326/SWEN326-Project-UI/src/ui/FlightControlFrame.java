package ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;

public class FlightControlFrame extends JFrame {

    private JPanel mapPanel;
    private JPanel flightManagementPanel;
    private JPanel autopilotControlPanel;
    private JPanel sensorDataDisplayPanel;
    private JPanel hazardAlertsPanel;
    private int height = 600;
    private int width = 1400;
    ImageIcon flightIcon;
    ImageIcon map;
    Border border;

    public FlightControlFrame() {

        this.flightIcon = new ImageIcon("Images/flight.png");
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

        flightManagementPanel = new JPanel();
        flightManagementPanel.setBounds(0, 0, (int) ((3.0 / 14.0) * width), (int) ((3.0 / 4.0) * height));
        flightManagementPanel.setBackground(Color.YELLOW);
        flightManagementPanel.setBorder(border);

        mapPanel = new JPanel();
        this.addMap(mapPanel);
        mapPanel.setBounds((int) ((3.0 / 14.0) * width), 0, (int) ((8.0 / 14.0) * width), (int) ((3.0 / 4.0) * height));
        mapPanel.setBorder(border);

        autopilotControlPanel = new JPanel();
        autopilotControlPanel.setBounds((int) ((11.0 / 14.0) * width), 0, (int) ((3.0 / 14.0) * width), (int) ((3.0 / 4.0) * height));
        autopilotControlPanel.setBackground(Color.RED);
        autopilotControlPanel.setBorder(border);

        sensorDataDisplayPanel = new JPanel();
        sensorDataDisplayPanel.setBounds(0, (int) ((3.0 / 4.0) * height), width / 2, height / 4);
        sensorDataDisplayPanel.setBackground(Color.DARK_GRAY);
        sensorDataDisplayPanel.setBorder(border);

        hazardAlertsPanel = new JPanel();
        hazardAlertsPanel.setBounds(width / 2, (int) ((3.0 / 4.0) * height), width / 2, height / 4);
        hazardAlertsPanel.setBackground(Color.PINK);
        hazardAlertsPanel.setBorder(border);

        this.add(flightManagementPanel);
        this.add(mapPanel);
        this.add(autopilotControlPanel);
        this.add(sensorDataDisplayPanel);
        this.add(hazardAlertsPanel);
    }

    // Add Map and Label to the center Panel
    public void addMap(JPanel panel) {
        this.map = new ImageIcon("Images/world_map.jpg");
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
