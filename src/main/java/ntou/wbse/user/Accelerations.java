package ntou.wbse.user;

import java.util.*;

public class Accelerations {
	
	private List<Acceleration> accelerations;

    public Accelerations() {
    	accelerations = new ArrayList<Acceleration>();
    }

    public void addAcceleration(double x, double y, double z, long timestamp) {
    	Acceleration acc = new Acceleration(x, y, z, timestamp);
    	
    	if ( accelerations.size() == 1000 )
    		accelerations.remove(0);
    	accelerations.add(acc);
    }

	public List<Acceleration> getAccelerations() {
		return accelerations;
	}

	public void setAccelerations(List<Acceleration> accelerations) {
		this.accelerations = accelerations;
	}

	@Override
	public String toString() {
		return "Accelerations [accelerations=" + accelerations + "]";
	}
}