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
    	
    	if ( locations.size() == 1000 ) 
    			locations.remove(0); 
    	locations.add(loc);
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