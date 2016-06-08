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

	@Override
	public void response() {
	}
}