import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdministratorInterface {
	
	private static Scanner scanner = new Scanner(System.in);
			
	public static void main(String[] args) {
        int choice;

        do {
            displayMenu(); //Display the menu and ask for a choice
            System.out.print("Enter Your Choice: ");

            try {//Use Try to catch the exception if a wrong input is given.
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        createStudent();
                        break;
                    case 2:
                    	showAllStudents();
                    	break;
                    case 3:
                        createCourse();
                        break;
                    case 4:
                    	showAllCourses();
                    	break;
                    case 5:
                        assignStudent();
                        break;
                    case 6:
                        setGradeForCourse();
                        break;
                    case 0:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid Choice. Please Try Again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); 
                choice = -1; // Set an invalid choice to continue loop
            }

        } while (choice != 0);

        scanner.close();
    }
	
	
	
	
	public static void displayMenu() {
		System.out.println("Choose Options:");
		System.out.println("1. Add New Student");
        System.out.println("2. Show All Student");
		System.out.println("3. Add New Course");
        System.out.println("4. Show All Courses");
		System.out.println("5. Assign Student To Course");
		System.out.println("6. Assign Grade To Course");
		System.out.println("0. Exit.");
	}
	
	public static void createStudent() {
		System.out.println("Student Name:");
		String studentName = scanner.nextLine(); //Ask for a string name
		
		CourseManagement.addStudent(studentName); //Create Student And Insert The Name
	}
	
	private static void createCourse() {
        System.out.print("Enter course code: "); //Input Course Code
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: "); //Input Course Name
        String courseName = scanner.nextLine();
        System.out.print("Enter maximum capacity: "); //Input Maximum Amount Of Students In A Course
        int maxCapacity = scanner.nextInt();
        
        CourseManagement.addCourse(courseCode, courseName, maxCapacity); //Create The Course

    }
	
	private static void assignStudent() {
	    System.out.print("Enter Student Id: ");
	    int studentId = scanner.nextInt();
	    scanner.nextLine(); 

	    Student foundStudent = CourseManagement.getStudentById(studentId); //Finding the student from the list

	    if (foundStudent != null) {
	        System.out.println("Found Student: " + foundStudent.getName());
	        System.out.print("Enter Course Code: ");
	        String courseCode = scanner.nextLine();

	        Course foundCourse = CourseManagement.getCourseByCode(courseCode); //finding the course from the list
	        if (foundCourse != null) {
	            if (foundCourse.isStudentEnrolled(foundStudent)) { //if the student is already enrolled in that course do nothing, else enroll them.
	                System.out.println(foundStudent.getName() + " is already enrolled in " + foundCourse.getName());
	            } else {
	                foundCourse.enrollStudent(foundStudent);
	                foundStudent.getCourses().add(foundCourse);
	                System.out.println(foundStudent.getName() + " was assigned to " + foundCourse.getName());
	            }
	        } else {
	            System.out.println("No Course Found With That Code...");
	        }
	    } else {
	        System.out.println("No Student Found With That Id...");
	    }
	}

	private static void setGradeForCourse() {
	    System.out.print("Enter Student Id: ");
	    int studentId = scanner.nextInt();
	    scanner.nextLine(); 

	    Student foundStudent = CourseManagement.getStudentById(studentId); //Same method as above to finding the right student and course.

	    if (foundStudent != null) {
	        System.out.println("Found Student: " + foundStudent.getName());
	        System.out.print("Enter Course Code: ");
	        String courseCode = scanner.nextLine();

	        Course foundCourse = CourseManagement.getCourseByCode(courseCode);
	        if (foundCourse != null) {
	            if (foundCourse.isStudentEnrolled(foundStudent)) {

	        	    System.out.print("Enter Grade For Course: ");
	        	    int gradeScore = scanner.nextInt();
	        	    
	        	    if(gradeScore<=100) { //The grade for course cannot be over 100
	        	    	CourseManagement.assignGrade(foundStudent, foundCourse, gradeScore);
	        	    }
	        	    else {
	        	    	System.out.print("The grade cannot be over 100%.");
	        	    }
	            	
	            	
	            	
	            } else {

	                System.out.println(foundStudent.getName() + " is not enrolled in " + foundCourse.getName());
	            }
	        } else {
	            System.out.println("No Course Found With That Code...");
	        }
	    } else {
	        System.out.println("No Student Found With That Id...");
	    }
	}
	
	private static void showAllStudents() {
	    List<Student> students = CourseManagement.getStudents();
	    for (Student student : students) {
	        System.out.println(" ");
	        System.out.println("Id: " + student.getId());
	        System.out.println("Name: " + student.getName());

	        List<Course> courses = student.getCourses();
	        System.out.println("Courses:");
	        for (Course course : courses) { //Loop each course.
	            System.out.println("- " + course.getName());

	            // Retrieve grades for this student in the current course
	            List<Integer> grades = CourseManagement.getGrades(student, course);
	            if (!grades.isEmpty()) {
	                System.out.println("  Grades:");
	                for (int grade : grades) {
	                    System.out.println("    " + grade+"%");
	                    
	                    
	                }
	                double averageGrade = CourseManagement.calculateAverageGrade(student, course); // Calculate the average and display it
                    System.out.println("  Average Grade: " + averageGrade+"%");
	            } else {
	                System.out.println("  No grades recorded for this course.");
	            }
	        }

	        System.out.println(" ");
	    }
	}

	
	
	private static void showAllCourses() {
	    List<Course> courses = CourseManagement.getCourses();
	    for (Course course : courses) {
	        System.out.println(" ");
	        System.out.println("Code: " + course.getCourseCode());
	        System.out.println("Name: " + course.getName());
	        System.out.println("Places: " + course.getEnrolledStudents().size() + " / " + course.getMaxCapacity());

	        List<Student> students = course.getEnrolledStudents();
	        System.out.println("Students:");
	        for (Student student : students) { //Loop each student
	            System.out.println("- " + student.getName());

	            // Retrieve grades for this student in the current course
	            List<Integer> grades = CourseManagement.getGrades(student, course); //Display the scores of each student
	            if (!grades.isEmpty()) {
	                System.out.println("  Grades:");
	                for (int grade : grades) {
	                    System.out.println("    " + grade+"%");
	                 
	                    
	                }
	                double averageGrade = CourseManagement.calculateAverageGrade(student, course); // And their average for this course.
                    System.out.println("  Average Grade: " + averageGrade+"%");
	            } else {
	                System.out.println("  No grades recorded for this student in this course.");
	            }
	        }

	        System.out.println(" ");
	    }
	}

	

}
