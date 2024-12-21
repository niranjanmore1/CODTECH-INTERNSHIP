import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Subject {
    private String name;
    private double grade;

    public Subject(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

class GradeTrackerSystem {
    private HashMap<String, ArrayList<Subject>> studentData;

    public GradeTrackerSystem() {
        studentData = new HashMap<>();
    }

    public void addStudent(String studentName) {
        if (studentData.containsKey(studentName)) {
            System.out.println("ERROR: Student \"" + studentName + "\" already exists.");
        } else {
            studentData.putIfAbsent(studentName, new ArrayList<>());
            System.out.println("SUCCESS: Student \"" + studentName + "\" has been added.");
        }
    }

    public void addSubject(String studentName, String subjectName, double grade) {
        ArrayList<Subject> subjects = studentData.get(studentName);
        if (subjects != null) {
            if (grade < 0 || grade > 100) {
                System.out.println("ERROR: Grade must be between 0 and 100.");
                return;
            }
            subjects.add(new Subject(subjectName, grade));
            System.out.println("SUCCESS: Subject \"" + subjectName + "\" with grade " + grade + " added for \"" + studentName + "\".");
        } else {
            System.out.println("ERROR: Student \"" + studentName + "\" does not exist. Please add the student first.");
        }
    }

    public void displayGrades(String studentName) {
        ArrayList<Subject> subjects = studentData.get(studentName);
        if (subjects != null && !subjects.isEmpty()) {
            System.out.println("\n--- Grades for " + studentName + " ---");
            double total = 0;
            for (Subject subject : subjects) {
                System.out.printf("%-20s: %.2f%n", subject.getName(), subject.getGrade());
                total += subject.getGrade();
            }
            double average = total / subjects.size();
            System.out.printf("\nAverage Grade: %.2f%n", average);
        } else if (subjects != null) {
            System.out.println("ERROR: \"" + studentName + "\" has no grades yet.");
        } else {
            System.out.println("ERROR: Student \"" + studentName + "\" not found.");
        }
    }

    public void listAllStudents() {
        if (studentData.isEmpty()) {
            System.out.println("No students are currently in the system.");
        } else {
            System.out.println("\n--- List of Students ---");
            for (String studentName : studentData.keySet()) {
                System.out.println("- " + studentName);
            }
        }
    }
}

class User {
    private String userId;
    private String password;
    private String role; // Role can be "admin" or "teacher"

    public User(String userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

public class Studentmgmt{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeTrackerSystem tracker = new GradeTrackerSystem();

        // Create sample users
        HashMap<String, User> users = new HashMap<>();
        users.put("admin", new User("admin", "admin123", "admin"));
        users.put("teacher", new User("teacher", "teacher123", "teacher"));

        displayWelcomeMessage();

        // Authentication
        String role = authenticateUser(users, scanner);
        if (role == null) {
            System.out.println("ERROR: Authentication failed.");
            return;
        }

        // Display menu based on role
        boolean isRunning = true;
        while (isRunning) {
            if (role.equals("admin")) {
                isRunning = displayAdminMenu(scanner, tracker);
            } else if (role.equals("teacher")) {
                isRunning = displayTeacherMenu(scanner, tracker);
            }
        }
         System.out.println("-------------------------------------------------------------------------");
        System.out.println("                          Thank you for using the system. Goodbye!");
         System.out.println("-------------------------------------------------------------------------");
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        System.out.println("                       ------------------------------------");
        System.out.println("                       WELCOME TO STUDENT MANAGEMENT SYSTEM");
        System.out.println("                       ------------------------------------");
        System.out.println("Day: " + now.format(dayFormatter));
        System.out.println("Date: " + now.format(dateFormatter));
        System.out.println("Time: " + now.format(timeFormatter));
    }

    private static String authenticateUser(HashMap<String, User> users, Scanner scanner) {
        while (true) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            System.out.println("-------------------------------------------------------------------------");

            User user = users.get(userId);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("\nWELCOME, " + userId + "!");
                return user.getRole();
            } else {
                System.out.println("ERROR: Invalid credentials. Please try again.");
            }
        }
    }

    private static boolean displayAdminMenu(Scanner scanner, GradeTrackerSystem tracker) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("ADMIN MENU:");
        System.out.println("1. Add a student");
        System.out.println("2. Add a subject and grade");
        System.out.println("3. Display grades for a student");
        System.out.println("4. List all students");
        System.out.println("5. Exit");
         System.out.println("-------------------------------------------------------------------------");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume 
         System.out.println("-------------------------------------------------------------------------");

        switch (choice) {
            case 1:
                System.out.print("Enter student name: ");
                tracker.addStudent(scanner.nextLine());
                break;
            case 2:
                addSubjectAndGrade(scanner, tracker);
                break;
            case 3:
                System.out.print("Enter student name: ");
                tracker.displayGrades(scanner.nextLine());
                break;
            case 4:
                tracker.listAllStudents();
                break;
            case 5:
                return false;
            default:
                System.out.println("ERROR: Invalid choice. Please try again.");
        }
        return true;
    }

    private static boolean displayTeacherMenu(Scanner scanner, GradeTrackerSystem tracker) {
        System.out.println("\nTEACHER MENU:");
        System.out.println("1. Add a subject and grade");
        System.out.println("2. Display grades for a student");
        System.out.println("3. List all students");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addSubjectAndGrade(scanner, tracker);
                break;
            case 2:
                System.out.print("Enter student name: ");
                tracker.displayGrades(scanner.nextLine());
                break;
            case 3:
                tracker.listAllStudents();
                break;
            case 4:
                return false;
            default:
                System.out.println("ERROR: Invalid choice. Please try again.");
        }
        return true;
    }

    private static void addSubjectAndGrade(Scanner scanner, GradeTrackerSystem tracker) {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter subject name: ");
        String subjectName = scanner.nextLine();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        tracker.addSubject(studentName, subjectName, grade);
    }
}
