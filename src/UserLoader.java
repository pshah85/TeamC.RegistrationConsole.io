import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: TEAM C: [PREMAL SHAH, HAMID ASGARI, MITA JAGAD, LISA GRADY]
 *Class: I&C SCI_X460.10 (WINTER 2016/REG 00216/SEC 1) (Java Programming I)
 *Instructor: KESHA SMITH
 *FINAL TEAM PROJECT: REGISTRATION CONSOLE
 *Email: Shah.Prem85@gmail.com
 * Created by TEam C on 2/23/2016.
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
