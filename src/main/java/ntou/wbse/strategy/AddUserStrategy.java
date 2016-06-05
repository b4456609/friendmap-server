package ntou.wbse.strategy;

import ntou.wbse.App;
import ntou.wbse.user.User;
import org.json.JSONObject;

import javax.websocket.Session;

/**
 * Created by bernie on 2016/6/5.
 */
public class AddUserStrategy extends ReceviceAndResponse {

    private final App app;
    private final String id;
    private final String name;
    private Session session;

    public AddUserStrategy(App app, JSONObject json, Session session){
        this.session = session;
        this.app = app;
        this.name = json.getString("name");
        this.id = json.getString("id");
    }

    @Override
    public void action() {
    	if(!app.getUserIdusers().containsKey(id)){
	        User user = new User(session, id, name);
	        app.getWaittingUsers().add(user);
	        app.getUserIdusers().put(id, user);
    	}
    	else{
    		app.getUserIdusers().get(id).setSession(session);
    	}
    }

    @Override
    public void response() {

    }
}
