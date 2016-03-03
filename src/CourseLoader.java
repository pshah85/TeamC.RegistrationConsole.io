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
    private static Scanner userInput = new Scanner(System.in);
    private static String newCourseFileName;
    public static String getCourseFileName() {
        return newCourseFileName;
    }

    public static Map<String, Course> loadCourses(String courseFileName) {
        boolean run = true;
        Map<String, Course> courses = new HashMap<>();
        do {
            File defaultCourseFile = new File(courseFileName);
                // Using try with resources automatically closes the readers
                try (BufferedReader br = new BufferedReader(new FileReader(defaultCourseFile))) {
                    // read the first line from the text file
                    String line;
                    // loop until all lines are read
                    while ((line = br.readLine()) != null) {
                        Course course = new Course(line);
                        courses.put(course.getId(), course);
                    }
                    newCourseFileName = courseFileName;
                    run = false;
                    break;
                    // close file stream
                } catch (IOException e) {
                    // Print out the exception that occurred
                    System.out.println("Unable to read File" + "\nError: " + e.getMessage());
                    System.out.println("Enter Valid File Path:");
                    courseFileName = userInput.nextLine();
                }
        } while (true);
        return courses;
    }

}
