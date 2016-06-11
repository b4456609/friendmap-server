package ntou.wbse.strategy;

import ntou.wbse.App;
import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import javax.websocket.Session;

/**
 * Created by bernie on 2016/6/5.
 */
public class AddUserStrategy extends ReceviceAndResponse {

	private final App app;
	private final String id;
	private final String name;
	private User user;
	private boolean isSuccess;

	private Session session;

	public AddUserStrategy(App app, JSONObject json, Session session) {
		this.isSuccess = true;
		this.session = session;
		this.app = app;
		this.name = json.getString("name");
		this.id = json.getString("id");
	}

	@Override
	public void action() {
		if (!app.getUserIdusers().containsKey(id)) {
			user = new User(id, name);
			user.setSession(session);
			app.getWaittingUsers().add(user);
			app.getUserIdusers().put(id, user);
		} else {
			user = app.getUserIdusers().get(id);
			user.setSession(session);
		}
	}

	public static String responseString(Group group, boolean isSuccess) {
		JSONObject jsonObj = new JSONObject();
		if (isSuccess) {
			JSONObject groupObj = new JSONObject();
			if (group != null) {
				groupObj.put("name", group.getName());
				groupObj.put("id", group.getId());
				JSONArray jsonArray = new JSONArray();
				for (User user : group.getMembers()) {
					JSONObject userObj = new JSONObject();
					userObj.put("name", user.getName());
					userObj.put("id", user.getId());
					jsonArray.put(userObj);
				}
				groupObj.put("member", jsonArray);
			}

			jsonObj.put("type", "loginResponse");
			jsonObj.put("group", groupObj);
			jsonObj.put("status", "success");
		} else {
			jsonObj.put("status", "fail");
			jsonObj.put("message", "fail");
		}
		return jsonObj.toString();
	}

	@Override
	public void response() {
		Group group = app.getUserIdGroup().get(user.getId());
		if (group != null)
			user.sendMessage(responseString(group, isSuccess));
		else
			user.sendMessage(responseString(null, isSuccess));
	}
}
