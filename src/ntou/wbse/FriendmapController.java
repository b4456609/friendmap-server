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

@ServerEndpoint(value = "/test")
public class FriendmapController {
	private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

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
		System.out.println("Message Received: " + message);
		// json資料開頭是{
		if (message.startsWith("{")) {
			JSONObject json = new JSONObject(message);
			System.out.println(json);
			dispatch(json);
		} else {
			userSession.getAsyncRemote().sendText(message);
		}
	}

	private void dispatch(JSONObject json) {
		String type = json.getString("type");
		switch (type) {
		case "addUser":
			System.out.println("addUser");
			String name = json.getString("name");
			String id = json.getString("id");			
			break;
		default:
			System.out.println("Type error");
		}
	}
	
}
