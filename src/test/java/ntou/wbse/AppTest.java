package ntou.wbse;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.*;

public class AppTest {
	private App app;

	@Before
	public void setUp() {
		app = App.getInstance();
	}

	private String getFromFile(String filename) {
		try {
			URI uri = AppTest.class.getClassLoader().getResource(filename).toURI();
			String string = new String(Files.readAllBytes(Paths.get(uri)), "utf-8");
			return string;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void testaddUser2Group() {
		Group group = new Group(null, 1464767827233L, new User(null, "2134548458",  "王建民"));
		group.addUser2Group(new User(null, "2134548453",  "陳偉殷"));			
		String jsonFromFile =new  JSONObject(getFromFile("addUser2Group.json")).toString();
		assertEquals(jsonFromFile, app.user2GroupJson(false, group, null));
	}

}
