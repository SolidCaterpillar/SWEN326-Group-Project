package fcs;

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
        
        this.yaw = yaw_;    // yaw is relative to north
        this.pitch = 0;    // plane starts from level
        this.roll = 0;    // starts from level
        
        this.minThurst = minThrust_;
        this.maxThurst = maxThurst_;
    }
    
    /**
     * Inside the set methods we can add assertion statements and checks to ensure
     * the new data is correct/does not violate constraints
     */

    // get and update the pa=lances location, speed, and thrust
    public double getSpeed() { return this.speed; }
    public void setSpeed(double speed1) { this.speed = speed1; }
    
    public double getThrust() { return this.thrust; }
    public void setThrust(double thrust1) { this.thrust = thrust1; }
    
    public double getAltitude() { return this.altitude; }
    public void setAltitude(double altitude1) { this.altitude = altitude1; }
    
    public double getLatitude() { return this.latitude; }
    public void setLatitude(double latitude1) { this.latitude = latitude1; }
    
    public double getLongitude() { return this.longitude; }
    public void setLongitude(double longitude1) { this.longitude = longitude1; }
    
    // plane orientation
    public double getYaw() { return this.yaw; }
    public void updateYaw(double yaw1) { this.yaw = yaw1; }
    
    public double getPitch() { return this.pitch; }
    public void updatePitch(double pitch1) { this.pitch = pitch1; }
    
    public double getRoll() { return this.roll; }
    public void updateRoll(double roll1) { this.roll = roll1; }
    
    // min and max thrusts
    public double getMinThrust() { return this.minThurst; }
    public double getMaxThrust() { return this.maxThurst; }
    
}
