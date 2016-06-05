package ntou.wbse.user;


import java.util.*;

/**
 * 
 */
public class GPS {

    /**
     * Default constructor
     */
    public GPS() {
    }

    /**
     * 
     */
    private List<Location> locations;


    /**
     * 
     */
    public void addLocation() {
        // TODO implement here
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