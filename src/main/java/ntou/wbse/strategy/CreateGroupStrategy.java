package ntou.wbse.strategy;

import ntou.wbse.App;
import ntou.wbse.FriendmapController;
import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bernie on 2016/6/5.
 */
public class CreateGroupStrategy extends ReceviceAndResponse {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateGroupStrategy.class);
    private String userId;
    private String groupName;
    private long groupId;
    private App app;
    private User owner;

    private boolean isSuccess;


    public CreateGroupStrategy(App app, JSONObject json){
        this.userId = json.getString("userId");
        this.groupName = json.getString("name");
        this.groupId = json.getLong("id");
        this.app = app;
    }

    @Override
    public void action() {
        isSuccess = true;
        owner = app.getUserIdusers().get(userId);
        Group group = new Group(groupName, groupId, owner);
        // add to group list
        app.getGroups().add(group);
        // add to user id to group map
        app.getUserIdGroup().put(userId, group);
        // delete this user from waittinguser
        LOGGER.debug( app.getWaittingUsers().toString());
        app.getWaittingUsers().remove(owner);
        LOGGER.debug( app.getWaittingUsers().toString());
    }

    public static String responseString(boolean isSuccess){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "createGroupResult");
        if (isSuccess) {
            jsonObj.put("status", "success");
        }
        return jsonObj.toString();
    }

    @Override
    public void response() {
        owner.sendMessage(responseString(isSuccess));
    }
}
