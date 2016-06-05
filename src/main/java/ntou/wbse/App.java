package ntou.wbse;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntou.wbse.user.User;

/**
 * 
 */
public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendmapController.class);

	/**
	 * map userid to group key value pair
	 */
	private Map<String, Group> userIdGroup;

	/**
	 * all groups
	 */
	private List<Group> groups;

	/**
	 * waiting for add to group user
	 */
	private List<User> waittingUsers;

	/**
	 * waiting for add to group user
	 */
	private Map<String, User> userIdusers;

	/**
	 * Default constructor
	 */
	private static App instance = null;

	public Map<String, Group> getUserIdGroup() {
		return userIdGroup;
	}

	public void setUserIdGroup(Map<String, Group> userIdGroup) {
		this.userIdGroup = userIdGroup;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<User> getWaittingUsers() {
		return waittingUsers;
	}

	public void setWaittingUsers(List<User> waittingUsers) {
		this.waittingUsers = waittingUsers;
	}

	public Map<String, User> getUserIdusers() {
		return userIdusers;
	}

	public void setUserIdusers(Map<String, User> userIdusers) {
		this.userIdusers = userIdusers;
	}

	private App() {
		userIdGroup = new HashMap<String, Group>();
		groups = new ArrayList<Group>();
		waittingUsers = new ArrayList<User>();
		userIdusers = new HashMap<String, User>();
	}

	synchronized static public App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public Group getGroupById(long id){
		Group group = null;
		for (Group g : groups) {
			if (g.getId() == id) {
				return g;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "App [userIdGroup=" + userIdGroup + ", groups=" + groups + ", waittingUsers=" + waittingUsers
				+ ", userIdusers=" + userIdusers + "]";
	}

}