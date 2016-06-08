package ntou.wbse.user;

import java.util.*;

/*enum StatusList {
	Normal, TrafficLights, Break, Toilet, 
    Critical, Accident, TrafficJam
}*/

public class Status {
	private String status;
	private long timestamp;
	private Date date;
	
	public Status() {
		date = new Date();
		this.status = "Normal";
		this.timestamp = date.getTime();
	}
	
	public void setStatus(String status, long timestamp) {
		this.status = status;
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
