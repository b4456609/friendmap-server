package ntou.wbse;

import java.io.IOException;
import java.util.*;

import javax.websocket.Session;

/**
 * 
 */
public class User {

	/**
	 * Default constructor
	 */

	/**
	 * 
	 */
	private Session session;

	/**
	 * 
	 */
	private String id;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private GPS gps;

	/**
	 * 
	 */
	private Accerlations accerlations;

	public User(Session session, String id, String name) {
		this.session = session;
		this.id = id;
		this.name = name;
		this.gps = new GPS();
		this.accerlations = new Accerlations();
	}

	/**
	 * @param lon
	 * @param lat
	 */
	public void updateLocation(double lon, double lat) {
		// TODO implement here
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public void updateAccerlation(double x, double y, double z) {
		// TODO implement here
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

	public Accerlations getAccerlations() {
		return accerlations;
	}

	public void setAccerlations(Accerlations accerlations) {
		this.accerlations = accerlations;
	}

}