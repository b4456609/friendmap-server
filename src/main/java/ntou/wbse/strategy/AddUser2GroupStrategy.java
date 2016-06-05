package ntou.wbse.strategy;

import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONArray;
import org.json.JSONObject;

import ntou.wbse.App;

public class AddUser2GroupStrategy extends ReceviceAndResponse{

	private App app;
	private String userId;
	private long groupId;
	private Group group;

	public AddUser2GroupStrategy(App app, JSONObject json){
		this.app = app;
		this.userId = json.getString("userId");
		this.groupId = json.getLong("groupId");
	}

	@Override
	public void action() {
		//find group and user and add user to group
		group = app.getGroupById(groupId);
		User user = app.getUserIdusers().get(userId);
		group.addUser2Group(user);

		//remove from waitting user
		app.getWaittingUsers().remove(user);
	}

	public static String responseString(Group group){
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (User user : group.getMembers()) {
			JSONObject userObj = new JSONObject();
			userObj.put("name", user.getName());
			userObj.put("id", user.getId());
			jsonArray.put(userObj);
		}

		jsonObj.put("type", "addUser2Group");
		jsonObj.put("status", "success");
		jsonObj.put("groupId", group.getId());
		jsonObj.put("user", jsonArray);
		return jsonObj.toString();
	}

	@Override
	public void response() {
		group.sendMessage2All(responseString(group));
	}
}
