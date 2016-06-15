package ntou.wbse.strategy;

import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ntou.wbse.App;

public class AddUser2GroupStrategy extends ReceviceAndResponse {

	private App app;
	private String userId;
	private long groupId;
	private Group group;
	
	private boolean isSuccess;
	private String message;

	public AddUser2GroupStrategy(App app, JSONObject json) {
		try {
			this.isSuccess = true;
			this.app = app;
			this.userId = json.getString("userId");
			this.groupId = json.getLong("groupId");
		} catch (JSONException e) {
			isSuccess = false;
			message = e.toString();
		}
	}

	@Override
	public void action() {
		if (isSuccess) {
			// find group and user and add user to group
			group = app.getGroupById(groupId);
			User user = app.getUserIdusers().get(userId);
			group.addUser2Group(user);

			// remove from waitting user
			app.getWaittingUsers().remove(user);
		}
	}

	public static String responseString(Group group, boolean isSuccess) {
		JSONObject jsonObj = new JSONObject();
		if (isSuccess) {
			JSONArray jsonArray = new JSONArray();
			for (User user : group.getMembers()) {
				JSONObject userObj = new JSONObject();
				userObj.put("name", user.getName());
				userObj.put("id", user.getId());
				jsonArray.put(userObj);
			}

			jsonObj.put("type", "addUser2Group");
			jsonObj.put("status", "success");
			//User user = app.getUserIdusers().get(userId);
			//jsonObj.put("userAddedID", user.getId());
			jsonObj.put("groupId", group.getId());
			jsonObj.put("user", jsonArray);
		}
		else{
			jsonObj.put("status", "fail");
			jsonObj.put("message", "fail");
		}
		return jsonObj.toString();
	}

	@Override
	public void response() {
		group.sendMessage2All(responseString(group, isSuccess));
	}
}
