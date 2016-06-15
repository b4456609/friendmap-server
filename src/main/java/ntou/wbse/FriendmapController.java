package ntou.wbse;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ntou.wbse.strategy.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/test")
public class FriendmapController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendmapController.class);
	private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	private App app = App.getInstance();
	private Strategy strategy;

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
		String type = json.getString("type");
		switch (type) {
		case "addUser":
			LOGGER.debug("addUser");
			strategy = new AddUserStrategy(app, json, userSession);
			strategy.execute();
			break;
		case "webAppLogin":
			LOGGER.debug("webAppLogin");
			strategy = new UpdateStatusStrategy(app, json);
			strategy.execute();
			break;
		case "createGroup":
			LOGGER.debug("createGroup");
			strategy = new CreateGroupStrategy(app, json);
			strategy.execute();
			break;
		case "searchPeople":
			LOGGER.debug("searchPeople");
			strategy = new SearchPeopleStrategy(app, userSession);
			strategy.execute();
			break;
		case "addUser2Group":
			LOGGER.debug("addUser2Group");
			strategy = new AddUser2GroupStrategy(app, json);
			strategy.execute();
			break;
		case "leaveGroup":
			LOGGER.debug("leaveGroup");
			strategy = new LeaveGroupStrategy(app, json);
			strategy.execute();
			break;
		case "updateLocation":
			LOGGER.debug("updateLocation");
			strategy = new UpdateLocationStrategy(app, json);
			strategy.execute();
			break;
		case "updateAcceleration":
			LOGGER.debug("updateAcceleration");
			strategy = new UpdateAccelerationStrategy(app, json);
			strategy.execute();
			break;
		case "updateStatus":
			LOGGER.debug("updateStatus");
			strategy = new UpdateStatusStrategy(app, json);
			strategy.execute();
			break;
		
		default:
			LOGGER.debug("Type error");
		}
	}

}