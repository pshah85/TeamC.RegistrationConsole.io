import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Author: TEAM C: [PREMAL SHAH, HAMID ASGARI, MITA JAGAD, LISA GRADY]
 *Class: I&C SCI_X460.10 (WINTER 2016/REG 00216/SEC 1) (Java Programming I)
 *Instructor: KESHA SMITH
 *FINAL TEAM PROJECT: REGISTRATION CONSOLE
 *Email: Shah.Prem85@gmail.com
 * Created by TEam C on 2/23/2016.
 */
public class UserLoader {
    private static Scanner userInput = new Scanner(System.in);
    private static String newUserFileName;
    public static String getUserFileName() {
        return newUserFileName;
    }

    public static Map<String, User> loadUsers(String userFileName) {
        boolean run = true;
        Map<String, User> users = new HashMap<>();
        // try with resources automatically closes readers
        do {
            File defaultUserFile = new File(userFileName);
            try (BufferedReader br = new BufferedReader(new FileReader(defaultUserFile))) {
                // read the first line from the text file
                String line;
                // loop until all lines are read
                while ((line = br.readLine()) != null) {
                    User user = new User(line);
                    users.put(user.getUserId(), user);
                }
                newUserFileName = userFileName;
                run = false;
                break;
            } catch (IOException e) {
                // Print out the exception that occurred
                System.out.println("Unable to read file" + "\nError: " + e.getMessage());
                System.out.println("Enter Valid File Path:");
                userFileName = userInput.nextLine();
            }
        }while(true);
        return users;
    }
}
