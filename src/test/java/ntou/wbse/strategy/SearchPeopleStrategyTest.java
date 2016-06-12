package ntou.wbse.strategy;

import ntou.wbse.user.User;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by bernie on 2016/6/5.
 */
public class SearchPeopleStrategyTest {
    @Test
    public void responseString() throws Exception {
        User user1 = new User("2134548458", "王建民");
        User user = new User("2134548457", "Tom");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user);

        String jsonFromFile = new JSONObject(TestUtil.getFromFile("searchPeopleResult.json")).toString();
        assertEquals(jsonFromFile, SearchPeopleStrategy.responseString(users));
    }

}