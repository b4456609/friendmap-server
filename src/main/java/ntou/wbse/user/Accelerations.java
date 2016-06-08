package ntou.wbse.user;

import java.util.*;

public class Accelerations {
	
	private List<Acceleration> accelerations;

    public Accelerations() {
    	accelerations = new ArrayList<Acceleration>();
    }

    public void addAcceleration(double x, double y, double z, long timestamp) {
    	int i;
    	
    	Acceleration acc = new Acceleration(x, y, z, timestamp);
    	accelerations.add(acc);
    	if ( accelerations.size() > 1000 ) 
    		for (i=1; i<=1000; i++)
    			accelerations.set(i-1, accelerations.get(i)); 
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