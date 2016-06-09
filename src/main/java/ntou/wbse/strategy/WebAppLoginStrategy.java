package ntou.wbse.strategy;

import javax.websocket.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import ntou.wbse.App;
import ntou.wbse.Group;
import ntou.wbse.user.User;

public class WebAppLoginStrategy extends ReceviceAndResponse  {

	 private final App app;
	    private final String id;
	    private final String name;
	    
	    private Session session;

	    public WebAppLoginStrategy(App app, JSONObject json, Session session) {
	        	this.session = session;
	            this.app = app;
	            this.name = json.getString("name");
	            this.id = json.getString("id");
	    }

	    @Override
	    public void action() {
	    	if(!app.getUserIdusers().containsKey(id)){
		        User user = new User(id, name);
		        user.setWebSession(session);
		        app.getWaittingUsers().add(user);
		        app.getUserIdusers().put(id, user);
	    	}
	    	else{
	    		app.getUserIdusers().get(id).setWebSession(session);
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

				jsonObj.put("type", "webAppLogin");
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
	    	
	    }
}
