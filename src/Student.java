import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private int id;  // Non-static instance variable
    private List<Course> enrolledCourses;
    
    private static int nextId = 1;  // Static variable to track the next available ID
    
	
	
	public Student(String name) {
		this.name = name;
	    this.id = nextId; 
	    nextId++; 
		this.enrolledCourses = new ArrayList<>();

		
	}
	
	
	//Methods to return private variables
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Course> getCourses(){
		return enrolledCourses;
	}
	
	
	
    public static void enrollStudent(Student student, Course course) { //Enroll The Given Student To The Course


        course.enrollStudent(student);
    	
    }

	
}
