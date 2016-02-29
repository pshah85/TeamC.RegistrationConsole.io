import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shahp on 2/24/2016.
 */
public class UserLoader {

    public static Map<String, User> Loadusers() {

        Map<String, User> defaultusers = new HashMap<>();
        File defaultUserFile = new File("resources/users.txt");
        // try with resources automatically closes readers
        try (BufferedReader br = new BufferedReader(new FileReader(defaultUserFile))) {
            // read the first line from the text file
            String line;
            // loop until all lines are read
            while ((line = br.readLine()) != null) {
                User user = new User(line);
                defaultusers.put(user.getUserId(), user);
            }
        } catch (IOException e) {
            // Print out the exception that occurred
            System.out.println("Unable to read " + defaultUserFile + ": " + e.getMessage());
        }
        return defaultusers;
    }

}
