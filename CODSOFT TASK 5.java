#CODSOFT TASK 5: 
#STUDENT COURSE REGISTRATION SYSTEM 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
 String code;
 String title;
 String description;
 int capacity;
 String schedule;

 Course(String code, String title, String description, int capacity, String schedule) {
 this.code = code;
 this.title = title;
 this.description = description;
 this.capacity = capacity;
 this.schedule = schedule;
 }
}

class Student {
 String id;
 String name;
 List<Course> registeredCourses;

 Student(String id, String name) {
 this.id = id;
 this.name = name;
 this.registeredCourses = new ArrayList<>();
 }
}

public class CourseRegistrationSystem {
 static Map<String, Course> courses = new HashMap<>();
 static Map<String, Student> students = new HashMap<>();

 public static void main(String[] args) {
 initializeCourses(); // Initialize some courses for testing
 Scanner scanner = new Scanner(System.in);
 int choice;
 do {
 System.out.println("1. Course Listing");
 System.out.println("2. Student Registration");
 System.out.println("3. Course Removal");
 System.out.println("4. Exit");
 System.out.print("Enter your choice: ");
 choice = scanner.nextInt();
 switch (choice) {
 case 1:
 displayCourseListing();
 break;
 case 2:
 registerStudent(scanner);
 break;
 case 3:
 removeCourse(scanner);
 break;
 case 4:
 System.out.println("Exiting...");
 break;
 default:
 System.out.println("Invalid choice. Please try again.");
 }
 } while (choice != 4);
 scanner.close();
 }

 static void initializeCourses() {
 courses.put("CSE101", new Course("CSE101", "Introduction to Computer Science", "An introduction to the basics of computer science.", 50, "Monday 10:00 AM - 12:00 PM"));
 courses.put("CSE201", new Course("CSE201", "Data Structures and Algorithms", "Study of fundamental data structures and algorithms.", 40, "Wednesday 2:00 PM - 4:00 PM"));
 courses.put("CSE301", new Course("CSE301", "Database Management Systems", "Introduction to database concepts and design.", 30, "Friday 9:00 AM - 11:00 AM"));
 }

 static void displayCourseListing() {
 System.out.println("Course Listing:");
 for (Course course : courses.values()) {
 System.out.println("Code: " + course.code);
 System.out.println("Title: " + course.title);
 System.out.println("Description: " + course.description);
 System.out.println("Capacity: " + course.capacity);
 System.out.println("Schedule: " + course.schedule);
 System.out.println();
 }
 }

 static void registerStudent(Scanner scanner) {
 System.out.print("Enter student ID: ");
 String id = scanner.next();
 System.out.print("Enter student name: ");
 String name = scanner.next();
 Student student = new Student(id, name);
 students.put(id, student);

 boolean continueRegistering = true;

 while (continueRegistering) {
 System.out.println("Available Courses:");
 displayCourseListing();

 System.out.print("Enter course code to register: ");
 String courseCode = scanner.next();
 Course course = courses.get(courseCode);
 if (course != null && course.capacity > 0) {
 if (!student.registeredCourses.contains(course)) {
 student.registeredCourses.add(course);
 course.capacity--;
 System.out.println("Student " + name + " registered for course " + course.title + " successfully.");
 } else {
 System.out.println("Student is already registered for this course.");
 }
 } else {
 System.out.println("Invalid course code or course is full.");
 }

 System.out.print("Do you want to register for another course? (yes/no): ");
 String response = scanner.next();
 continueRegistering = response.equalsIgnoreCase("yes");
 }
 }

 static void removeCourse(Scanner scanner) {
 System.out.print("Enter student ID: ");
 String id = scanner.next();
 Student student = students.get(id);
 if (student != null) {
 System.out.println("Registered courses for student " + student.name + ":");
 for (Course course : student.registeredCourses) {
 System.out.println(course.code + ": " + course.title);
 }
 System.out.print("Enter course code to remove: ");
 String code = scanner.next();
 Course courseToRemove = null;
 for (Course course : student.registeredCourses) {
 if (course.code.equals(code)) {
 courseToRemove = course;
 break;
 }
 }
 if (courseToRemove != null) {
 student.registeredCourses.remove(courseToRemove);
 courseToRemove.capacity++;
 System.out.println("Course " + courseToRemove.title + " removed successfully.");
 } else {
 System.out.println("Course not found.");
 }
 } else {
 System.out.println("Student not found.");
 }
 }
}
