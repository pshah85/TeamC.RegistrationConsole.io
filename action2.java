import java.util.*;
public class action2{

	public static void add(student student,course course){

		if(course.getCapacity()==course.getNumberOfStudentsRegistered()){
			System.out.println("Class is Full");
		}
		else if ((student.getSelectedCourses()).contains(course.getCourseID())){
			System.out.println("You have already Registered for this course");
		}
		else{
			student.addNewCourse(course);
			course.addNewStudent(student.getStudentID());
			System.out.println("You have successfully registered for the following Course: " + course.getCourseID() + "  " +course.getCourseName());
		}
	}

	public static void drop(student student,course course){

		(student.getSelectedCourses()).remove(course.getCourseID());
		(course.getListOfRegisteredStudents()).remove(student.getStudentID());
		System.out.println("You have successfully dropped the following Course: " + course.getCourseID() + "  " + course.getCourseName());
	}

}