package ntou.wbse.strategy;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by bernie on 2016/6/5.
 */
public class TestUtil {
    public static String getFromFile(String filename) {
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
}
