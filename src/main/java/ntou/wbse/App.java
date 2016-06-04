package ntou.wbse;

import java.util.*;

import javax.websocket.Session;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class App {
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendmapController.class);

	/**
	 * 
	 */
	private Map<String, Group> userIdGroup;

	/**
	 * 
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

	/**
	 * @param id
	 * @param name
	 */
	public void login(Session session, String id, String name) {
		User user = new User(session, id, name);
		waittingUsers.add(user);
		userIdusers.put(id, user);
	}

	/**
	 * @param name
	 * @param id
	 * @param userId
	 */
	public void createGroup(String name, long id, String userId) {
		User owner = userIdusers.get(userId);
		Group group = new Group(name, id, owner);
		// add to group list
		groups.add(group);
		// add to user id to group map
		userIdGroup.put(userId, group);
		// delete this user from waittinguser
		for (User user : waittingUsers) {
			if (user.getId().equals(userId)) {
				waittingUsers.remove(user);
				break;
			}
		}
		String json = createGroupResultJson( true, null);
		owner.sendMessage(json);
	}

	/**
	 * @param userId
	 * @param groupId
	 */
	public void addUser2Group(String userId, long groupId) {
		Group group = null;
		User user = null;
		for (Group g : groups) {
			if (g.getId() == groupId) {
				group = g;
				break;
			}
		}
		user = userIdusers.get(userId);
		LOGGER.debug(user.toString());
		LOGGER.debug(group.toString());
		waittingUsers.remove(user);
		group.addUser2Group(user);
		String json = user2GroupJson(true, group, null);
		group.sendMessage2All(json);
	}

	public String user2GroupJson(boolean b, Group group, String message) {
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (User user : group.getMembers()) {
			JSONObject userObj = new JSONObject();
			userObj.put("name", user.getName());
			userObj.put("id", user.getId());
			jsonArray.put(userObj);
		}

		jsonObj.put("type", "addUser2Group");
		jsonObj.put("status", "success");
		jsonObj.put("groupId", group.getId());
		jsonObj.put("user", jsonArray);

		return jsonObj.toString();
	}

	/**
	 * @param userId
	 */
	public void userLeaveGroup(String userId) {
		// TODO implement here
	}

	/**
	 * @param id
	 * @param lon
	 * @param lat
	 * @param timestamp
	 */
	public void updateUserLocation(String id, double lon, double lat, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param id
	 * @param x
	 * @param y
	 * @param z
	 * @param timestamp
	 */
	public void updateUserAccerlation(String id, double x, double y, double z, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param id
	 * @param status
	 */
	public void updateUserStatus(String id, String status) {
		// TODO implement here
	}

	/**
	 * @param user
	 * @param isSuccess
	 * @param message
	 * @return 
	 */
	private String createGroupResultJson(boolean isSuccess, String message) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", "CreateGroupResult");
		if (isSuccess == true) {
			jsonObj.put("status", "success");
		}
		return jsonObj.toString();
	}

	/**
	 * @param userSession
	 * @param users
	 * @return 
	 */
	public String searchResultjson() {
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (User user : waittingUsers) {
			JSONObject userObj = new JSONObject();
			userObj.put("name", user.getName());
			userObj.put("id", user.getId());
			jsonArray.put(userObj);
		}

		jsonObj.put("type", "searchPeopleResult");
		jsonObj.put("search", jsonArray);

		return jsonObj.toString();
	}

	/**
	 * @param userId
	 * @param lon
	 * @param lat
	 * @param timestamp
	 */
	private void updateLocationJson(String userId, double lon, double lat, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param userId
	 * @param x
	 * @param y
	 * @param z
	 * @param timestamp
	 */
	private void endUpdateAccelerationJson(String userId, double x, double y, double z, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param userId
	 * @param status
	 * @param timestamp
	 */
	private void updateStatusJson(String userId, String status, long timestamp) {
		// TODO implement here
	}

	@Override
	public String toString() {
		return "App [userIdGroup=" + userIdGroup + ", groups=" + groups + ", waittingUsers=" + waittingUsers
				+ ", userIdusers=" + userIdusers + "]";
	}

}