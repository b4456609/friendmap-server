package ntou.wbse.strategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ntou.wbse.App;
import ntou.wbse.Group;
import ntou.wbse.user.User;

public class UpdateLocationStrategy extends ReceviceAndResponse {

	private App app;
	private String userId;
	private double lon;
	private double lat;
	private long timestamp;

	private boolean isSuccess;
	private String message;

	public UpdateLocationStrategy(App app, JSONObject json) {
		try {
			this.isSuccess = true;
			this.app = app;
			this.userId = json.getString("userId");
			this.lon = json.getDouble("lon");
			this.lat = json.getDouble("lat");
			this.timestamp = json.getLong("timestamp");
		} catch (JSONException e) {
			isSuccess = false;
			message = e.toString();
		}
	}

	@Override
	public void action() {
		if (isSuccess) {
			// update the user's location
			User user = app.getUserIdusers().get(userId);
			user.updateLocation(lon, lat, timestamp);
		}
	}

	public static String responseString(String userId, Double lon, Double lat, long timestamp) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", "updateLocation");
		jsonObj.put("lon", lon);
		jsonObj.put("lat", lat);
		jsonObj.put("userId", userId);
		jsonObj.put("timestamp", timestamp);

		return jsonObj.toString();
	}

	@Override
	public void response() {
		Group group = app.getUserIdGroup().get(userId);
		if (group != null)
			group.sendMessageFromUserId(userId, responseString(userId, lon, lat, timestamp));
	}
}
