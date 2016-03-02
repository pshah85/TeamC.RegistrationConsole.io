/**
 * Author: TEAM C: [PREMAL SHAH, HAMID ASGARI, MITA JAGAD, LISA GRADY]
 *Class: I&C SCI_X460.10 (WINTER 2016/REG 00216/SEC 1) (Java Programming I)
 *Instructor: KESHA SMITH
 *FINAL TEAM PROJECT: REGISTRATION CONSOLE
 *Email: Shah.Prem85@gmail.com
 * Created by TEam C on 2/23/2016.
 */
class Course {

    private String id;
    private String name;
    private String cdate;
    private int capacity;
    private int enrolled;
    private String status;

    public Course(String record){
        String[] fields = record.split(";",-1);
        this.id = fields[0];
        this.name = fields[1];
        this.cdate = fields[2];
        this.capacity = Integer.parseInt(fields[3]);
        this.enrolled = Integer.parseInt(fields[4]);
        this.status = fields[5];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getCdate() { return cdate; }

    public void setCdate(String cdate) { this.cdate = cdate;}

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

    public String getStatus() { return status;}

    public void setStatus(String status) {
        this.status = status;
    }

    public void registeredStudent(){
        enrolled++;
        if (capacity == enrolled){
            status = "CLOSED";
        }
    }
    public void unregisterStudent(){
        enrolled--;
        if (capacity > enrolled){
            status = "OPEN";
        }
    }

    @Override
    public String toString() {
        return "" + "[" + id + "]" +
                " " + name.toUpperCase() + ":" +
                " [OFFERING: " + cdate + "]" +
                " [CAP: " + capacity + "]" +
                " [ENROLLED: " + enrolled + "]" +
                " STATUS: [" + status + "]";
    }


    public String report() {
        String sb = "" + id + ";" +
                name + ";" +
                cdate + ";" +
                capacity + ";" +
                enrolled + ";" +
                status + "";
        // to do
        return sb;
    }
}
