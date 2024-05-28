package ui;
/**
 * Waypoint that contains coordinates, time of arrival and speed restriction information
 */
public record WayPoint(String name, Coordinate coordinate, double speedRestriction, String time){}