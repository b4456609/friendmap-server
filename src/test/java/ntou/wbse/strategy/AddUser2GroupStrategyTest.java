package ntou.wbse.strategy;

import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONObject;
import org.junit.Test;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by bernie on 2016/6/5.
 */
public class AddUser2GroupStrategyTest {

    private String getFromFile(String filename) {
        try {
            URI uri = AddUser2GroupStrategyTest.class.getClassLoader().getResource(filename).toURI();
            String string = new String(Files.readAllBytes(Paths.get(uri)), "utf-8");
            return string;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void responseString() throws Exception {

        Group group = new Group(null, 1464767827233L, new User(null, "2134548458",  "王建民"));
        group.addUser2Group(new User(null, "2134548453",  "陳偉殷"));
        String jsonFromFile =new JSONObject(getFromFile("addUser2Group.json")).toString();

        assertEquals(jsonFromFile, AddUser2GroupStrategy.responseString(group));
    }

}