import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * Created by Shahp on 2/24/2016.
 */
public class CourseLoader {

    public static Map<String, Course> loadCourses() {
        Map<String, Course> courses = new HashMap<>();
        File coursesFile = new File("resources/courses.txt");
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
