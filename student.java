import java.util.*;
public class student{

	public String firstName;
	public String lastName;
	public int studentID;
	public ArrayList<Integer> selectedCourses=new ArrayList<Integer>();
	public int numberOfClassesRegistered;

	public student(String firstName, String lastName, int studentID){
		this.firstName=firstName;
		this.lastName=lastName;
		this.studentID=studentID;
		this.numberOfClassesRegistered=0;
	}

	//getters

	public String getFullName(){
		return(firstName+" "+lastName);
	}

	public int getStudentID(){
		return studentID;
	}

	public ArrayList<Integer> getSelectedCourses(){
		return selectedCourses;
	}
	//setters

	public void addNewCourse(course course){
		selectedCourses.add(course.getCourseID());
	}
}