package ntou.wbse.strategy;

import ntou.wbse.Group;
import ntou.wbse.user.User;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeaveGroupStrategyTest {
	
	 @Test
	    public void responseString() throws Exception {
		 	Group group = new Group(null, 1464767827233L, new User("2134548458", "王建民"));
		 	String jsonFromFile = new JSONObject(TestUtil.getFromFile("leaveGroup.json")).toString();
		 	assertEquals(jsonFromFile, LeaveGroupStrategy.responseString(group, true));
	    }
	
}
