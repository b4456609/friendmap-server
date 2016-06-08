package ntou.wbse.user;

import java.io.IOException;
import java.util.*;

import javax.websocket.Session;

/**
 * 
 */
public class User {

	private Session session;
	private String id;
	private String name;
	private GPS gps;
	private Accelerations accelerations;
	private Status status;

	public User(Session session, String id, String name) {
		this.session = session;
		this.id = id;
		this.name = name;
		this.gps = new GPS();
		this.accelerations = new Accelerations();
		this.status = new Status();
	}

	public void updateLocation(double lon, double lat, long timestamp) {
		gps.addLocation(lon, lat, timestamp);
	}

	public void updateAcceleration(double x, double y, double z, long timestamp) {
		accelerations.addAcceleration(x, y, z, timestamp);
	}
	
	public void updateStatus(String status, long timestamp) {
		this.status.setStatus(status, timestamp);
	}

	/**
	 * @param message
	 */
	public void sendMessage(String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

	public Accelerations getAccelerations() {
		return accelerations;
	}

	public void setAccelerations(Accelerations accelerations) {
		this.accelerations = accelerations;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gps=" + gps + ", accelerations=" + accelerations + "]";
	}

}