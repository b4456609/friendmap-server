package ntou.wbse;

import java.util.*;

import javax.websocket.Session;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntou.wbse.user.User;

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

	/**
	 * @param id
	 * @param name
	 */
	public void login(Session session, String id, String name) {
		User user = new User(session, id, name);
		waittingUsers.add(user);
		userIdusers.put(id, user);
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

	/**
	 * @param userId
	 */
	public void userLeaveGroup(String userId) {
		// TODO implement here
		// 用userId去userIdGroup裡面找group
		//把這個user物件從group刪除
		//把這個user加入到waittinguser
	}

	/**
	 * @param id
	 * @param lon
	 * @param lat
	 * @param timestamp
	 */
	public void updateUserLocation(String id, double lon, double lat, long timestamp) {
		// TODO implement here
		//找到user物件更新gps位置
		//通知所有群組內的人除了他自己 gps座標
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
	public void updateLocationJson(String userId, double lon, double lat, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param userId
	 * @param x
	 * @param y
	 * @param z
	 * @param timestamp
	 */
	public void endUpdateAccelerationJson(String userId, double x, double y, double z, long timestamp) {
		// TODO implement here
	}

	/**
	 * @param userId
	 * @param status
	 * @param timestamp
	 */
	public void updateStatusJson(String userId, String status, long timestamp) {
		// TODO implement here
	}

	@Override
	public String toString() {
		return "App [userIdGroup=" + userIdGroup + ", groups=" + groups + ", waittingUsers=" + waittingUsers
				+ ", userIdusers=" + userIdusers + "]";
	}

}