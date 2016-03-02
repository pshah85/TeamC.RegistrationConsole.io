import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * Author: TEAM C: [PREMAL SHAH, HAMID ASGARI, MITA JAGAD, LISA GRADY]
 *Class: I&C SCI_X460.10 (WINTER 2016/REG 00216/SEC 1) (Java Programming I)
 *Instructor: KESHA SMITH
 *FINAL TEAM PROJECT: REGISTRATION CONSOLE
 *Email: Shah.Prem85@gmail.com
 * Created by TEam C on 2/23/2016.
 */
public class RegistrationConsole {

    private static Scanner userInput = new Scanner(System.in);

    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Course> courses = new HashMap<>();
    private static String currentUserId;
    private static int nameLength;
    public static void mainMenu() {
        System.out.println( "----------------------------------------------------------------------------");
        System.out.println( "|Author(s): TEAM C: [PREMAL SHAH, HAMID ASGARI, MITA JAGAD, LISA GRADY]    |\n"+
                            "|Class: I&C SCI_X460.10 (WINTER 2016/REG 00216/SEC 1) (Java Programming I) |\n"+
                            "|Instructor: KESHA SMITH                                                   |\n"+
                            "|FINAL TEAM PROJECT: REGISTRATION CONSOLE                                  |\n"+
                            "|Email: Shah.Prem85@gmail.com                                              |");
        System.out.println( "----------------------------------------------------------------------------\n\n");
        boolean run = true;
        users = UserLoader.Loadusers();
        courses = CourseLoader.loadCourses();
        int userSelection;
        do {
            try {
                System.out.println("**********************************************");
                System.out.println("Welcome to UCI Extension Registration Console");
                System.out.print(" 1.New User Registration \n" +
                                 " 2.My Account Login \n" +
                                 " 3.Course Catalogue \n" +
                                 " 4.Student Directory\n" +
                                 " 5.Exit \n");
                System.out.print("**********************************************\n"+
                                   "Enter Valid Choice: ");

                userSelection = userInput.nextInt();
                switch (userSelection) {
                    case 1:                     //New User Registration RegistrationConsole
                        registerNewUser();
                        break;
                    case 2:                     //Current User Login Display RegistrationConsole
                        users.clear();
                        users = UserLoader.Loadusers();
                        login();
                        currentUserMenu();
                        break;
                    case 3:                     // Course Listing Display RegistrationConsole
                        courseCatalogue();
                        break;
                    case 4:                     //Student Database Display
                        studentDirectory();
                        break;
                    case 5:                     //Exit Program
                        System.out.println("Application Shutting Down ....");
                        run = false;
                        System.exit(0);
                        break;
                    default:                    //Invalid User Input
                        System.out.println("Invalid User input\n");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid User Input\n");
                userInput.nextLine();
            }
        } while (run);
        return;
    }

    private static void saveToFile() {
        System.out.println("Now recording registration data to user's file");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/courses.txt"))) {
            for(Course course : courses.values()) {
                bufferedWriter.write(course.report());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/users.txt"))) {
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
        Map<String, Course> sortedCourses = new TreeMap<>(courses);
        nameLength = 0;
        for (Course c : sortedCourses.values()) {
            if ((c.getId().length() + c.getName().length() + 1) > nameLength) {
                nameLength = c.getId().length() + c.getName().length() + 1;
            }
        }
        printoutline();
        for (Course course : sortedCourses.values()) {
                System.out.print("["+course.getId().toUpperCase() +"] "+course.getName().toUpperCase());
                for(int i = 0; i < (nameLength-(course.getId().length()+course.getName().length())); i++){
                    System.out.print(" ");
                }
                System.out.print("["+ course.getCdate() + "] "+"CAPACITY: [" + course.getCapacity()+"] ENROLLED: ["+course.getEnrolled() + "] "+ " STATUS: [ " +course.getStatus().toUpperCase() +" ]");
                System.out.println("");
            }
        printoutline();
    }
    public static void studentDirectory(){         //Display Current Users in System
        System.out.println("Followig students are attending UCI:\n");
        Map<String, User> sortedUsers = new TreeMap<>(users);
        for (User u : sortedUsers.values()){
            System.out.println(u.getFirstName().toUpperCase() + " " + u.getLastName().toUpperCase());
        }
        System.out.println("\n");
        return;
    }

    // User Course Enrolled List
    public static void printEnrolledCourses() {
        Map<String, Course> sortedCourses = new TreeMap<>(courses);
        nameLength = 0;
        for (String c : users.get(currentUserId).getRegisteredCourseIds()) {
            if ((!users.get(currentUserId).getRegisteredCourseIds().isEmpty()) || (!users.get(currentUserId).getRegisteredCourseIds().equals(""))) {
                nameLength = nameLength - (sortedCourses.get(c).getId().length() + sortedCourses.get(c).getName().length());
            }
        }
        printoutline();
        for (String courseId : users.get(currentUserId).getRegisteredCourseIds()) {
            if ((!users.get(currentUserId).getRegisteredCourseIds().isEmpty()) || (!users.get(currentUserId).getRegisteredCourseIds().equals("")))  {
                System.out.print("["+sortedCourses.get(courseId).getId().toUpperCase() +"] "+sortedCourses.get(courseId).getName().toUpperCase());
                for(int i = 0; i < (nameLength-(sortedCourses.get(courseId).getId().length()+sortedCourses.get(courseId).getName().length())); i++){
                    System.out.print(" ");
                }
                System.out.print("["+ sortedCourses.get(courseId).getCdate() + "] "+"CAPACITY: [" + sortedCourses.get(courseId).getCapacity()+"] ENROLLED: ["+sortedCourses.get(courseId).getEnrolled() + "] "+ " STATUS: [ " +sortedCourses.get(courseId).getStatus().toUpperCase() +" ]");
                System.out.println("");
            } else if((users.get(currentUserId).getRegisteredCourseIds().isEmpty()) || (users.get(currentUserId).getRegisteredCourseIds().equals(""))) {
                System.out.println("Your are not enrolled in any courses");
            }
        }
        printoutline();
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
                System.out.println("Saving new user in to database");
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/users.txt"))) {
                    for(User u : users.values()) {
                        bufferedWriter.write(u.report());
                        bufferedWriter.newLine();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }

                run = false;
            } else {
                System.out.println("This user already exisists.");
            }

        } while (run);

        return;
    }
    public static void currentUserMenu(){          // User Personal Space
        boolean run = true;
        int userSelection = 0;

        do {
            System.out.println("\n" + users.get(currentUserId).getFullName() + "'s Personal Space" + "\n" +
                    "1.Enrolled Courses\n" +
                    "2.Available Courses\n" +
                    "3.Add Course\n" +
                    "4.Remove Course\n" +
                    "5.Save To File\n" +
                    "6.Logout \n" +
                    "Enter Valid Choice: ");
            try {
                userSelection = userInput.nextInt();
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
                        removeCourse();
                        break;
                    case 5:
                        saveToFile();
                        break;
                    case 6:
                        saveToFile();
                        currentUserId = "";
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid User Input");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid User Input\n");
                userInput.nextLine();
            }
        }while (run);
            return;
    }
    private static void availableCourses() {
        Map<String, Course> sortedCourses = new TreeMap<>(courses);
        nameLength = 0;
        for (Course c : sortedCourses.values()) {
            if ((c.getId().length() + c.getName().length() + 1) > nameLength) {
                nameLength = c.getId().length() + c.getName().length() + 1;
            }
        }
        printoutline();
        for (Course course : sortedCourses.values()) {
            if ((course.getEnrolled() < course.getCapacity()) && !(users.get(currentUserId).getRegisteredCourseIds().contains(course.getId()))) {
                System.out.print("["+course.getId().toUpperCase() +"] "+course.getName().toUpperCase());
                for(int i = 0; i < (nameLength-(course.getId().length()+course.getName().length())); i++){
                    System.out.print(" ");
                }
                System.out.print("["+ course.getCdate() + "] "+"CAPACITY: [" + course.getCapacity()+"] ENROLLED: ["+course.getEnrolled() + "] "+ " STATUS: [ " +course.getStatus().toUpperCase() +" ]");
                System.out.println("");
            }
        }
        printoutline();
    }
    private static void addCourse() {
        availableCourses();
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

        } while (false);
        return;

    }
    private static void removeCourse() {
        printEnrolledCourses();
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

    public static void printoutline(){
        int maxLength = 0;
        for (Course c : courses.values()) {
            if (c.toString().length() > maxLength) {
                maxLength = c.toString().length();
            }
        }
        for (int i = 0; i < maxLength; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        mainMenu();
    }

}
