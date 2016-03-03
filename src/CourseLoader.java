import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

class CourseLoader {
    private static String courseFileName;
    private static Scanner userInput = new Scanner(System.in);
    public static Map<String, Course> loadCourses() {
        Map<String, Course> courses = new HashMap<>();
        System.out.println("courses.txt File Path:");
        courseFileName = userInput.nextLine();
        File coursesFile = new File(courseFileName);
        // Using try with resources automatically closes the readers
        try (BufferedReader br = new BufferedReader(new FileReader(coursesFile))) {
            // read the first line from the text file
            String line;
            // loop until all lines are read
            while ((line = br.readLine()) != null) {
                Course course = new Course(line);
                courses.put(course.getId(), course);
            }
            // close file stream
        } catch (IOException e) {
            // Print out the exception that occurred
            System.out.println("Unable to read " + coursesFile + ": " + e.getMessage());
        }
        return courses;
    }

}
