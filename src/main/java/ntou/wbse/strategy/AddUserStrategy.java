package ntou.wbse.strategy;

import ntou.wbse.App;
import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import javax.websocket.Session;

/**
 * Created by bernie on 2016/6/5.
 */
public class AddUserStrategy extends ReceviceAndResponse {

    private final App app;
    private final String id;
    private final String name;
    private User user;
    private boolean isSuccess;

    
    private Session session;

    public AddUserStrategy(App app, JSONObject json, Session session) {
    		this.isSuccess = true;
        	this.session = session;
            this.app = app;
            this.name = json.getString("name");
            this.id = json.getString("id");
    }

    @Override
    public void action() {
    	if(!app.getUserIdusers().containsKey(id)){
	        user = new User(id, name);
	        user.setSession(session);
	        app.getWaittingUsers().add(user);
	        app.getUserIdusers().put(id, user);
    	}
    	else{
    		app.getUserIdusers().get(id).setSession(session);
    	}
    }
    
	public static String responseString(User user, boolean isSuccess) {
		JSONObject jsonObj = new JSONObject();
		if (isSuccess) {
			JSONArray jsonArray = new JSONArray();
			JSONObject userObj = new JSONObject();
			userObj.put("name", user.getName());
			userObj.put("id", user.getId());
			jsonArray.put(userObj);

			jsonObj.put("type", "addUser");
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
    	 user.sendMessage(responseString(user, isSuccess));
    }
}
