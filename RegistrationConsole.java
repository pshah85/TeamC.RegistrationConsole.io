import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Shahp on 2/24/2016.
 */
public class RegistrationConsole {

    private static Scanner userInput = new Scanner(System.in);

    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Course> courses = new HashMap<>();

    private  static String currentUserId;


    public static void mainMenu() {
        boolean run = true;
        users = UserLoader.Loadusers();
        courses = CourseLoader.loadCourses();
        int userSelection;
        do {
            try {
                System.out.println("Welcome to UCI Extension Registration Console");
                System.out.println("1.New User Registration \n" +
                        "2.My Account Login \n" +
                        "3.Course Catalogue \n" +
                        "4.Student Directory\n" +
                        "5.Save To File\n" +
                        "6.Exit \n" +
                        "Enter Valid Choice: ");
                userSelection = userInput.nextInt();
                switch (userSelection) {
                    case 1:                     //New User Registration RegistrationConsole
                        registerNewUser();
                        break;
                    case 2:                     //Current User Login Display RegistrationConsole
                        login();
                        currentUserMenu();
                        break;
                    case 3:                     // Course Listing Display RegistrationConsole
                        courseCatalogue();
                        break;
                    case 4:                     //Student Database Display
                        studentDirectory();
                        break;
                    case 5:
                        saveToFile();
                        break;
                    case 6:                     //Exit Program
                        System.out.println("Application Shutting Down ....");
                        run = false;
                        System.exit(0);
                        break;
                    default:                    //Invalid User Input
                        System.out.println("Invalid User input\n");
                        throw new IllegalArgumentException("Invalid Input");
                }
            }catch(IllegalArgumentException iae){
                //do stuff like print stack trace or exit
                return;
            }
        } while (run);
    }

    private static void saveToFile() {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/coursesReport.txt"))) {

            for(Course course : courses.values()) {
                bufferedWriter.write(course.report());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/usersReport.txt"))) {

            for(User user : users.values()) {
                bufferedWriter.write(user.report());
                bufferedWriter.newLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void courseCatalogue(){           //Generic Course Schedule RegistrationConsole
        System.out.println("UCI Extension Course Schedule");
        System.out.println("----------------------------------------------------------------------");
        for (Course c : courses.values()) {
            System.out.println(c);
        }
        System.out.println("----------------------------------------------------------------------");
        return;
    }
    public static void studentDirectory(){         //Display Current Users in System
        System.out.println("Followig students are attending UCI:\n");
        for (User u : users.values()){
            System.out.println(u.getFirstName().toUpperCase() + " " + u.getLastName().toUpperCase());
        }
        System.out.println("\n\n");
        return;
    }

    // User Course Enrolled List
    public static void printEnrolledCourses() {

        for (String courseId : users.get(currentUserId).getRegisteredCourseIds()) {
            if (!users.get(currentUserId).getRegisteredCourseIds().isEmpty()) {
                System.out.println(courses.get(courseId));
            } else {
                System.out.println("Your are not enrolled in any courses");
            }

        }
    }
    public static void registerNewUser() {        // New User Registration RegistrationConsole

        boolean run = true;
        do {
            System.out.println("New User Profile \n"+
                    "Enter First Name: ");
            String firstName = userInput.next();
            System.out.println("Enter Last Name: ");
            String lastName = userInput.next();
            System.out.println("Enter User ID: ");
            String userId = userInput.next();
            System.out.println("Enter Password: ");
            String password = userInput.next();
            userInput.nextLine();
            User user = new User(firstName, lastName, userId, password);
            if(!users.containsKey(user.getUserId())) {
                users.put(user.getUserId(), user);
                System.out.println("User successfully created.");
                run = false;
            } else {
                System.out.println("This user already exisists.");
            }

        } while (run);

        return;
    }
    public static void currentUserMenu(){          // User Personal Space
        boolean run = true;
        do {
            System.out.println("\n" + users.get(currentUserId).getFullName() + "'s Personal Space" + "\n" +
                    "1.Enrolled Courses\n" +
                    "2.Available Courses\n" +
                    "3.Add Course\n" +
                    "3.Remove Course\n" +
                    "4.Logout \n" +
                    "Enter Valid Choice: ");
            int userSelection = userInput.nextInt();
            userInput.nextLine();
            switch (userSelection) {
                case 1:
                    printEnrolledCourses();
                    break;
                case 2:
                    availableCourses();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    currentUserId = "";
                    run = false;
                    break;
                default:
                    System.out.println("Invalid User Input");
            }
        }while (run);
            return;
    }
    private static void availableCourses() {
        for (Course course : courses.values()) {
            if ((course.getEnrolled() < course.getCapacity()) && !(users.get(currentUserId).getRegisteredCourseIds().contains(course.getId()))) {
                System.out.println(course);
            }
        }
    }
    private static void addCourse() {
        boolean run = true;
        do {
            System.out.println("Enter Valid Course Id");
            String courseId = userInput.next();
            userInput.nextLine();
            if (!courses.containsKey(courseId)) {
                System.out.println("Invalid Course Id");
                break;
            } else if (users.get(currentUserId).getRegisteredCourseIds().contains(courseId)) {
                System.out.println("You are already enrolled in this course");
                break;
            } else if (courses.get(courseId).getEnrolled() >= courses.get(courseId).getCapacity()) {
                System.out.println("This course is already full");
                break;
            } else {
                users.get(currentUserId).addCourse(courseId);
                courses.get(courseId).registeredStudent();
                System.out.println("You have successfully registered for " + courseId);
                run = false;
            }
            // add logic to exit and invalid message

        } while (run);
        return;

    }
    private static void removeCourse() {
        boolean run = true;
        do {
            System.out.println("Enter Valid Course Id");
            String courseId = userInput.next();
            userInput.nextLine();
            if (!courses.containsKey(courseId)) {
                System.out.println("Invalid Course Id");
                break;
            } else if (!(users.get(currentUserId).getRegisteredCourseIds().contains(courseId))) {
                System.out.println("You are not enrolled in this course");
                break;
            } else if(!(courses.get(courseId).getEnrolled() == 0) && !(courses.get(courseId).getCapacity() == 0) && (users.get(currentUserId).getRegisteredCourseIds().contains(courseId))) {
                users.get(currentUserId).removeCourse(courseId);
                courses.get(courseId).unregisterStudent();
                System.out.println("You have successfully removed " + courseId);
                run = false;
            }
            // add logic to exit and invalid message

        } while (run);
        return;

    }
    public static void login(){         //Log In Screen
        boolean run = true;
        System.out.println("My Account Login");
        do{
            System.out.print("User ID: ");
            String userId = userInput.next();
            System.out.println("Password: ");
            String password = userInput.next();
            boolean isValiduser = isValiduser(userId,password);
            if (isValiduser){
                currentUserId = userId;
                run = false;
            } else {
                System.out.println("Invalid Credentials");
                // add logic to break out of login phase or retry
            }
        }while (run);
        return;
    }

    // Login User and Password Validation
    public static boolean isValiduser (String userId, String password) {
        if (users.containsKey(userId)) {
            if (password.equals(users.get(userId).getPassword())) {
                return true;
            }
        } else {
            System.out.println("User does not exist!");
        }
        return false;
    }

    public static void main(String[] args) {
        mainMenu();
    }

}
