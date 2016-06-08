package ntou.wbse.user;

import java.util.*;

public class Location {

    private double lon;
    private double lat;
    private long timestamp;

    public Location(double lon, double lat, long timestamp) {
    	this.lon = lon;
    	this.lat = lat;
    	this.timestamp = timestamp;
    }
    
	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Location [lon=" + lon + ", lat=" + lat + ", timestamp=" + timestamp + "]";
	}

}