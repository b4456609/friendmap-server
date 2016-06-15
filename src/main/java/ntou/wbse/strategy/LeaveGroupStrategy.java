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
	private User user;

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
			user = app.getUserIdusers().get(userId);
			group.userLeave(user.getId());

			// add to waitting user
			app.getWaittingUsers().add(user);

			// if group are no member in it remove it
			if(group.getMembers().isEmpty()){
				app.getGroups().remove(group);
				app.getUserIdGroup().remove(user.getId(), group);				
			}
		}
	}

	public static String responseString(String userId, String userName, boolean isSuccess) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", "userLeaveGroup");
		if (isSuccess) {
			jsonObj.put("userId", userId);
			jsonObj.put("userName", userName);
		} else {
			jsonObj.put("status", "fail");
			jsonObj.put("message", "fail");
		}
		return jsonObj.toString();
	}

	@Override
	public void response() {
		try {
			group.sendMessage2All(responseString(userId, user.getName(), isSuccess));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}