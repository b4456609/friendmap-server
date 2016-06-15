package ntou.wbse.strategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ntou.wbse.App;
import ntou.wbse.Group;
import ntou.wbse.user.User;

public class UpdateStatusStrategy extends ReceviceAndResponse {

	private App app;
	private String userId;
	private String status;
	private long timestamp;
	
	private boolean isSuccess;
	private String message;

	public UpdateStatusStrategy(App app, JSONObject json) {
		try {
			this.isSuccess = true;
			this.app = app;
			this.userId = json.getString("userId");
			this.status = json.getString("status");
			this.timestamp = json.getLong("timestamp");
		} catch (JSONException e) {
			isSuccess = false;
			message = e.toString();
		}
	}

	@Override
	public void action() {
		if (isSuccess) {
			// update user's status
			User user = app.getUserIdusers().get(userId);
			user.updateStatus(status, timestamp);
		}
	}
	
	public static String responseString(String userId, String status, long timestamp) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", "updateStatus");
		jsonObj.put("userId", userId);
		jsonObj.put("status", status);
		jsonObj.put("timestamp", timestamp);

		return jsonObj.toString();
	}

	@Override
	public void response() {
		Group group = app.getUserIdGroup().get(userId);
		if(group != null)
			group.sendMessageFromUserId(userId, responseString(userId, status, timestamp));
	}
}