package ntou.wbse.strategy;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntou.wbse.App;
import ntou.wbse.FriendmapController;
import ntou.wbse.Group;
import ntou.wbse.user.User;

public class UpdateLocationStrategy extends ReceviceAndResponse {
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendmapController.class);

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
			
			LOGGER.debug(userId);
			LOGGER.debug("" + lon);
			LOGGER.debug("" + lat);
		} catch (JSONException e) {
			LOGGER.debug("UpdateLocationStrategy error" + e.toString());
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
	
    public static String responseString(String userId, double lon, double lat, long timestamp){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "updateLocation");
        jsonObj.put("userId", userId);
        jsonObj.put("lon", lon);
        jsonObj.put("lat", lat);
        jsonObj.put("timstamp", timestamp);

        return jsonObj.toString();
    }

	@Override
	public void response() {
		LOGGER.debug(app.getUserIdGroup().get(userId).toString());
		app.getUserIdGroup().get(userId).sendMessageFromUserId(userId, responseString(userId, lon, lat, timestamp));
	}
}
