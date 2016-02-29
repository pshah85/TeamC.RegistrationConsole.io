/**
 * Created by Shahp on 2/23/2016.
 */
public class Course {

    private String id;
    private String name;
    private int capacity;
    private int enrolled;
    private String status;

    public Course(String record){
        String[] fields = record.split(";",-1);
        this.id = fields[0];
        this.name = fields[1];
        this.capacity = Integer.parseInt(fields[2]);
        this.enrolled = Integer.parseInt(fields[3]);
        this.status = fields[4];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void registeredStudent(){
        enrolled++;
    }
    public void unregisterStudent(){
        enrolled--;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("[").append(id).append("]");
        sb.append(" ").append(name.toUpperCase()).append(":");
        sb.append(" [CAP: ").append(capacity).append("]");
        sb.append(" [ENROLLED: ").append(enrolled).append("]");
        sb.append(" STATUS: [").append(status).append("]");
        return sb.toString();
    }

    public String report() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(id).append(";");
        sb.append(name).append(";");
        sb.append(capacity).append(";");
        sb.append(enrolled).append(";");
        sb.append(status).append("");
        // to do
        return sb.toString();
    }
}
