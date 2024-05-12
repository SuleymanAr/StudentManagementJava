import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Course {
    private String courseCode;
    private String name;
    private int maxCapacity;
    private List<Student> enrolledStudents;
    private static int totalEnrolledStudents = 0;
    
    public Course(String courseCode, String name, int maxCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }
    
    // Get methods
    public String getCourseCode() {
        return courseCode;
    }
    
    public String getName() {
        return name;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    // Static method to retrieve total number of enrolled students
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
    
    // Method to enroll a student in the course
    public void enrollStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity) { //If the course has enough places.
            enrolledStudents.add(student);
            totalEnrolledStudents++;//Increase the total enrolled students

            
            System.out.println("Student " + student.getName() + " enrolled in " + this.name + " with grade 0");
        } else {
            System.out.println("Course " + this.name + " has reached its maximum capacity.");
        }
    }


    public boolean isStudentEnrolled(Student student) {
        if (enrolledStudents != null && student != null) {
            for (Student enrolledStudent : enrolledStudents) {
                if (enrolledStudent.getId() == student.getId()) { //Get the input student id to check with the enrolled student id
                    return true; // Student is already enrolled
                }
            }
        }
        return false; // Student is not enrolled
    }
}
