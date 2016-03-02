import java.util.*;
public class course{
	public String courseName;
	public int courseID;
	public String courseInstructor;
	public int capacity;
	public int numberOfStudentsRegistered;
	public ArrayList<Integer> listOfRegisteredStudents=new ArrayList<Integer>();

	public course(String courseName, int courseID, String courseInstructor, int capacity){
		this.courseName=courseName;
		this.courseID=courseID;
		this.courseInstructor=courseInstructor;
		this.capacity=capacity;
		this.numberOfStudentsRegistered=0;
	}


	//getters

	public String getCourseName(){
		return courseName;
	}

	public int getCourseID(){
		return courseID;
	}

	public String getCourseInstructor(){
		return courseInstructor;
	}

	public int getCapacity(){
		return capacity;
	}

	public int getNumberOfStudentsRegistered(){
		return numberOfStudentsRegistered;
	}

	public ArrayList<Integer> getListOfRegisteredStudents(){
		return listOfRegisteredStudents;
	}

	//setters

	public void addNewStudent(int id){
		listOfRegisteredStudents.add(id);
	}
}
