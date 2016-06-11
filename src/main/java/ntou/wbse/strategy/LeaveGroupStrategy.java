package ntou.wbse.strategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ntou.wbse.App;
import ntou.wbse.Group;
import ntou.wbse.user.User;

public class LeaveGroupStrategy extends ReceviceAndResponse {

	private App app;
	private String userId;
	private long groupId;
	private Group group;
	
	private boolean isSuccess;
	private String message;

	public LeaveGroupStrategy(App app, JSONObject json) {
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
			group.userLeave(user.getId());

			// add to waitting user
			app.getWaittingUsers().add(user);
			
			// if group are no member in it remove it
			app.getGroups().remove(group);
			app.getUserIdGroup().remove(user.getId(), group);
		}
	}
	
	public static String responseString(Group group, boolean isSuccess) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", "leaveGroup");
		if (isSuccess) {
			JSONArray jsonArray = new JSONArray();
			for (User user : group.getMembers()) {
				JSONObject userObj = new JSONObject();
				userObj.put("name", user.getName());
				userObj.put("id", user.getId());
				jsonArray.put(userObj);
			}

			jsonObj.put("user", jsonArray);
			jsonObj.put("groupId", group.getId());
			
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