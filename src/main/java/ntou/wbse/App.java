package ntou.wbse;

import java.util.*;

import javax.websocket.Session;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 */
public class App {

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
		groups.add(group);
		for (User user : waittingUsers) {
			if (user.getId().equals(userId)) {
				waittingUsers.remove(user);
				break;
			}
		}
		sendCreateGroupResult(owner, true, null);
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
		for (User u : waittingUsers) {
			if (u.getId().equals(userId)) {
				user = u;
				break;
			}
		}
		System.out.println(group);
		System.out.println(user);
		waittingUsers.remove(user);
		group.addUser2Group(user);
		sendAddUser2Group(true, group, null);
	}

	private void sendAddUser2Group(boolean b, Group group, String message) {
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

		group.sendMessage2All(jsonObj.toString());
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
	 */
	private void sendCreateGroupResult(User user, boolean isSuccess, String message) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", "CreateGroupResult");
		if (isSuccess == true) {
			jsonObj.put("status", "success");
		}
		user.sendMessage(jsonObj.toString());
	}

	/**
	 * @param userSession
	 * @param users
	 */
	public void sendSearchResult(Session userSession) {
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

		userSession.getAsyncRemote().sendText(jsonObj.toString());
	}

	/**
	 * @param userId
	 * @param lon
	 * @param lat
	 * @param timestamp
	 */
	private void sendUpdateLocation(String userId, double lon, double lat, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param userId
	 * @param x
	 * @param y
	 * @param z
	 * @param timestamp
	 */
	private void sendUpdateAcceleration(String userId, double x, double y, double z, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param userId
	 * @param status
	 * @param timestamp
	 */
	private void sendUpdateStatus(String userId, String status, long timestamp) {
		// TODO implement here
	}

	@Override
	public String toString() {
		return "App [userIdGroup=" + userIdGroup + ", groups=" + groups + ", waittingUsers=" + waittingUsers
				+ ", userIdusers=" + userIdusers + "]";
	}

}