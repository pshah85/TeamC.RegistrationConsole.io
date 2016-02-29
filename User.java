import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahp on 2/23/2016.
 */
public class User {

    private String firstName;
    private String lastName;
    private String userId;
    private String password;
    private List<String> regsiteredCoursesIds;

    public User(String record) {
        regsiteredCoursesIds = new ArrayList<>();
        String[] fields = record.split(";", -1);
        this.firstName = fields[0];
        this.lastName = fields[1];
        this.userId = fields[2];
        this.password = fields[3];
        for (int i = 4; i < fields.length; i++){
            this.regsiteredCoursesIds.add(fields[i]);
        }
    }

    public User(String firstName, String lastName, String userId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
        regsiteredCoursesIds = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRegisteredCourseIds() {
        return regsiteredCoursesIds;
    }

    public void setRegisteredCourseIds(List<String> registeredCourseIds) {
        this.regsiteredCoursesIds = registeredCourseIds;
    }

    public String getFullName(){
        return (firstName + " " + lastName).toUpperCase();
    }


    public boolean addCourse(String courseId) {
        return regsiteredCoursesIds.add(courseId);
    }

    public boolean removeCourse(String courseId) {
        return regsiteredCoursesIds.remove(courseId);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(" ").append(firstName);
        sb.append(" ").append(lastName);
        sb.append(" ").append(userId);
        sb.append(" ").append(password);
        sb.append(" ").append(regsiteredCoursesIds);
        return sb.toString();
    }

    public String report() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(firstName);
        sb.append(";").append(lastName);
        sb.append(";").append(userId);
        sb.append(";").append(password);
        sb.append(";").append(String.join(";",regsiteredCoursesIds));
        return sb.toString();
    }
}