package ntou.wbse;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/test")
public class FriendmapController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendmapController.class);
	private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	private App app = App.getInstance();

	/**
	 * Callback hook for Connection open events. This method will be invoked
	 * when a client requests for a WebSocket connection.
	 * 
	 * @param userSession
	 *            the userSession which is opened.
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		userSessions.add(userSession);
	}

	/**
	 * Callback hook for Connection close events. This method will be invoked
	 * when a client closes a WebSocket connection.
	 * 
	 * @param userSession
	 *            the userSession which is opened.
	 */
	@OnClose
	public void onClose(Session userSession) {
		userSessions.remove(userSession);
	}

	/**
	 * Callback hook for Message Events. This method will be invoked when a
	 * client send a message.
	 * 
	 * @param message
	 *            The text message
	 * @param userSession
	 *            The session of the client
	 */
	@OnMessage
	public void onMessage(String message, Session userSession) {
		LOGGER.debug("Message Received: " + message);
		// json資料開頭是{
		if (message.startsWith("{")) {
			JSONObject json = new JSONObject(message);
			System.out.println(json);
			dispatch(json, userSession);
		} else {
			userSession.getAsyncRemote().sendText(message);
		}
	}

	private void dispatch(JSONObject json, Session userSession) {
		System.out.println(app);
		String type = json.getString("type");
		switch (type) {
		case "addUser":
			LOGGER.debug("addUser");
			String name = json.getString("name");
			String id = json.getString("id");
			app.login(userSession, id, name);
			System.out.println(app);
			break;
		case "createGroup":
			System.out.println("createGroup");
			String userId = json.getString("userId");
			String groupName = json.getString("name");
			long groupId = json.getLong("id");
			app.createGroup(groupName, groupId, userId);
			System.out.println(app);
			break;
		case "searchPeople":
			System.out.println("searchPeople");
			app.sendSearchResult(userSession);
			break;
		case "addUser2Group":
			System.out.println("addUser2Group");
			String aUserId = json.getString("userId");
			long aGroupId = json.getLong("groupId");
			app.addUser2Group(aUserId, aGroupId);
			break;
		case "leaveGroup":
			System.out.println("leaveGroup");
			String lUserId = json.getString("userId");
			String lGroupId = json.getString("groupId");
			break;
		case "updateLocation":
			System.out.println("updateLocation");
			String ulUserId = json.getString("userId");
			double lon = json.getDouble("lon");
			double lat = json.getDouble("lat");
			long ulTimestamp = json.getLong("timestamp");
			break;
		case "updateAcceleration":
			System.out.println("updateAcceleration");
			String uaUserId = json.getString("userId");
			double x = json.getDouble("x");
			double y = json.getDouble("y");
			double z = json.getDouble("z");
			long uaTimestamp = json.getLong("timestamp");
			break;
		case "updateStatus":
			System.out.println("updateStatus");
			String usUserId = json.getString("userId");
			String status = json.getString("status");
			long usTimestamp = json.getLong("timestamp");
			break;
		default:
			System.out.println("Type error");
		}
	}

}