package ntou.wbse.strategy;

import ntou.wbse.App;
import ntou.wbse.user.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.websocket.Session;
import java.util.List;

/**
 * Created by bernie on 2016/6/5.
 */
public class SearchPeopleStrategy extends ReceviceAndResponse {
    private App app;
    private Session session;


    public SearchPeopleStrategy(App app, Session userSession){
        this.app = app;
        this.session = userSession;
    }

    @Override
    public void action() {
		app.cleanLeaveUser();
    }

    public static String responseString(List<User> waittingUsers){
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (User user : waittingUsers) {
            JSONObject userObj = new JSONObject();
            userObj.put("name", user.getName());
            userObj.put("id", user.getId());
            jsonArray.put(userObj);
        }

        jsonObj.put("type", "searchPeopleResult");
        jsonObj.put("search", jsonArray);

        return jsonObj.toString();
    }

    @Override
    public void response() {
        session.getAsyncRemote().sendText(responseString(app.getWaittingUsers()));
    }
}
