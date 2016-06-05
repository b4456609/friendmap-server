package ntou.wbse.strategy;

import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bernie on 2016/6/5.
 */
public class CreateGroupStrategyTest {
    @Test
    public void responseString() throws Exception {
        String jsonFromFile = new JSONObject(TestUtil.getFromFile("createGroup.json")).toString();

        assertEquals(jsonFromFile, CreateGroupStrategy.responseString(true));

    }

}