package fcs;

import fcs.FlightController.DangerState;

public class Aircraft {

    private double speed;
    private double thrust;
    private double altitude;
    private double latitude;
    private double longitude;
    
    private double yaw;
    private double pitch;
    private double roll;

    private final int minThurst;
    private final int maxThurst;
    
    private double autopilotStatus;
    
    public Aircraft(double alt, double lat, double lon, int minThrust_, int maxThurst_, double yaw_) {
        // assert the provided arguments are valid
        assert alt >= 0;
        assert lat >= -90 && lat <= 90;
        assert lon >= -180 && lat <= 180;
        assert minThrust_ >= 0;
        assert maxThurst_ >= minThrust_;
        assert yaw_ >= -180 && yaw_ <= 180;
        
        this.speed = 0;
        this.thrust = 0;
        this.altitude = alt;
        this.latitude = lat;
        this.longitude = lon;
        this.autopilotStatus = 0;
        
        this.yaw = yaw_;   // yaw is relative to north
        this.pitch = 0;    // plane starts from level
        this.roll = 0;    // starts from level
        
        this.minThurst = minThrust_;
        this.maxThurst = maxThurst_;
    }
    
    /**
     * Given a range of values ensure that a new piece of
     * data falls within the provided range
     * 
     * @param newVal, new piece of data
     * @param lowerBound, the minimum allowed value of newVal
     * @param upperBound, the maximum allowed value of newVal
     * @return, 0 if successful, -1 if not
     */
    private static int preCondition(double newVal, double lowerBound, double upperBound) {
    	if (newVal < lowerBound || newVal > upperBound) {
    		FlightController.currentDangerSate = DangerState.PILOT_ACTION_NEEDED;
    		return -1;
    	}
    	return 0;
    }
    
    public void setAutopilotStatus(double d) {
    	this.autopilotStatus = d;
    }
    public double getAutopilotStatus() {return this.autopilotStatus;}
    
    /**
     * Check that the new speed is within 2000 mph
     * @param speed1
     * @return whether the update was successful
     */
    public int setSpeed(double speed1) { 
    	// assuming the plane can't go faster than 1000
    	int check = preCondition(speed1, 0, 2000);
    	if (check == 0) this.speed = speed1;
    	return check;
    }
    public double getSpeed() { return this.speed; }
    
    /**
     * Check that the new thrust is within the min and max thrust
     * @param thrust1
     * @return whether the update was successful
     */
    public int setThrust(double thrust1) { 
    	int check = preCondition(thrust1, this.minThurst, this.maxThurst);
    	if (check == 0) this.thrust = thrust1; 
    	return check;
    }
    public double getThrust() { return this.thrust; }
    
    /**
     * Check that the new altitude is within 30,000 feet
     * @param altitude1
     * @return whether the update was successful
     */
    public int setAltitude(double altitude1) {
    	// assuming 50000 feet is the highest it can go
    	int check = preCondition(altitude1, 0, 30000);
    	if (check == 0) this.altitude = altitude1; 
    	return check;
    }
    public double getAltitude() { return this.altitude; }
    
    /**
     * Check that the new latitude is within -90 to 90 degrees
     * @param latitude1
     * @return whether the update was successful
     */
    public int setLatitude(double latitude1) {
    	int check = preCondition(latitude1, -90, 90);
    	if (check == 0) this.latitude = latitude1;
    	return check;
    }
    public double getLatitude() { return this.latitude; }
    
    /**
     * Check that the new longitude is within -180 to 180 degrees
     * @param longitude1
     * @return whether the update was successful
     */
    public int setLongitude(double longitude1) {
    	int check = preCondition(longitude1, -180, 180);    	
    	if (check == 0) this.longitude = longitude1; 
        return check;
    }
    public double getLongitude() { return this.longitude; }
    
    /**
     * Check that the new yaw is within -180 to 180 degrees
     * @param yaw1
     * @return whether the update was successful
     */
    public int setYaw(double yaw1) {
    	int check = preCondition(yaw1, -180, 180);
    	if (check == 0) this.yaw = yaw1;
        return check;
    }
    public double getYaw() { return this.yaw; }
    
    /**
     * Check that the new roll is within -60 to 60 degrees
     * @param roll1
     * @return whether the update was successful
     */
    public int setRoll(double roll1) {
    	int check = preCondition(roll1, -60, 60);
    	if (check == 0) this.roll = roll1; 
    	return check;
    }
    public double getRoll() { return this.roll; }
    
    /**
     * Check that the new pitch is within -30 to 30 degrees
     * @param pitch1
     * @return whether the update was successful
     */
    public int setPitch(double pitch1) { 
    	int check = preCondition(pitch1, -30, 30);
    	if (check == 0) this.pitch = pitch1; 
    	return check;
    }
    public double getPitch() { return this.pitch; }
    
    // min and max thrusts
    public double getMinThrust() { return this.minThurst; }
    public double getMaxThrust() { return this.maxThurst; }
    
}
