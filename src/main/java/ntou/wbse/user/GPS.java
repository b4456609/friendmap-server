package ntou.wbse.user;

import java.util.*;

public class GPS {

    private List<Location> locations;

    public GPS() {
    	locations = new ArrayList<Location>();
    }
    
    public void addLocation(double lon, double lat, long timestamp) {
    	int i;
    	
    	Location loc = new Location(lon, lat, timestamp);
    	locations.add(loc);
    	if ( locations.size() > 1000 ) 
    		for (i=1; i<=1000; i++)
    			locations.set(i-1, locations.get(i)); 
    }

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "GPS [locations=" + locations + "]";
	}
}