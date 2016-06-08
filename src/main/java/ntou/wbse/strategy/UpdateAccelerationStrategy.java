package ntou.wbse.strategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ntou.wbse.App;
import ntou.wbse.Group;
import ntou.wbse.user.User;

public class UpdateAccelerationStrategy extends ReceviceAndResponse {

	private App app;
	private String userId;
	private double x;
	private double y;
	private double z;
	private long timestamp;

	private boolean isSuccess;
	private String message;

	public UpdateAccelerationStrategy(App app, JSONObject json) {
		try {
			this.isSuccess = true;
			this.app = app;
			this.userId = json.getString("userId");
			this.x = json.getDouble("x");
			this.y = json.getDouble("y");
			this.z = json.getDouble("z");
			this.timestamp = json.getLong("timestamp");
		} catch (JSONException e) {
			isSuccess = false;
			message = e.toString();
		}
	}

	@Override
	public void action() {
		if (isSuccess) {
			// update the user's acceleration
			User user = app.getUserIdusers().get(userId);
			user.updateAcceleration(x, y, z, timestamp);
		}
	}

	@Override
	public void response() {
	}
}
