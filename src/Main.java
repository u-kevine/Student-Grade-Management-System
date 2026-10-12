import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GradeManager gradeManager = new GradeManager();
    private static StudentManager studentManager = new StudentManager(gradeManager);

    // The subjects the user can choose from.
    private static Subject[] coreSubjects = {
            new CoreSubject("Mathematics", "MTH101"),
            new CoreSubject("English", "ENG101"),
            new CoreSubject("Science", "SCI101")
    };
    private static Subject[] electiveSubjects = {
            new ElectiveSubject("Music", "MUS101"),
            new ElectiveSubject("Art", "ART101"),
            new ElectiveSubject("Physical Education", "PHE101")
    };

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter choice: ", 1, 5);
            System.out.println();

            if (choice == 1) {
                addStudent();
            } else if (choice == 2) {
                studentManager.viewAllStudents();
                pause();
            } else if (choice == 3) {
                recordGrade();
            } else if (choice == 4) {
                viewGradeReport();
            } else {
                System.out.println("Thank you for using Student Grade Management System!");
                System.out.println("Goodbye!");
                running = false;
            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("============================================");
        System.out.println("   STUDENT GRADE MANAGEMENT - MAIN MENU");
        System.out.println("============================================");
        System.out.println();
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Record Grade");
        System.out.println("4. View Grade Report");
        System.out.println("5. Exit");
        System.out.println();
    }

    // ---------------- Add Student ----------------
    private static void addStudent() {
        System.out.println("ADD STUDENT");
        System.out.println("---------------------------------------------");

        String name = readText("Enter student name: ");
        int age = readInt("Enter student age: ", 5, 100);
        String email = readEmail("Enter student email: ");
        String phone = readText("Enter student phone: ");

        System.out.println();
        System.out.println("Student type:");
        System.out.println("1. Regular Student (Passing grade: 50%)");
        System.out.println("2. Honors Student (Passing grade: 60%, honors recognition)");
        int type = readInt("Select type (1-2): ", 1, 2);

        // The variable is a Student, the object is Regular OR Honors (polymorphism).
        Student student;
        if (type == 1) {
            student = new RegularStudent(name, age, email, phone);
        } else {
            student = new HonorsStudent(name, age, email, phone);
        }
        studentManager.addStudent(student);

        System.out.println();
        System.out.println("Student added successfully!");
        student.displayStudentDetails();
        pause();
    }

    // ---------------- Record Grade ----------------
    private static void recordGrade() {
        System.out.println("RECORD GRADE");
        System.out.println("---------------------------------------------");

        String id = readText("Enter Student ID: ");
        Student student = studentManager.findStudent(id);
        if (student == null) {
            System.out.println("Student not found: " + id);
            pause();
            return;
        }

        System.out.println();
        System.out.println("Name: " + student.getName());
        System.out.println("Type: " + student.getStudentType() + " Student");
        System.out.println();

        System.out.println("Subject type:");
        System.out.println("1. Core Subject");
        System.out.println("2. Elective Subject");
        int typeChoice = readInt("Select type (1-2): ", 1, 2);

        Subject[] options;
        if (typeChoice == 1) {
            options = coreSubjects;
        } else {
            options = electiveSubjects;
        }

        System.out.println();
        System.out.println("Available subjects:");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i].getSubjectName());
        }
        int subjectChoice = readInt("Select subject (1-3): ", 1, 3);
        Subject subject = options[subjectChoice - 1];

        double value = readGrade("Enter grade (0-100): ");

        Grade grade = new Grade(student.getStudentId(), subject, value);

        System.out.println();
        System.out.println("GRADE CONFIRMATION");
        System.out.println("---------------------------------------------");
        grade.displayGradeDetails();
        System.out.println("---------------------------------------------");

        String answer = readText("Confirm grade? (Y/N): ");
        if (answer.equalsIgnoreCase("Y")) {
            gradeManager.addGrade(grade);
            System.out.println("Grade recorded successfully!");
        } else {
            System.out.println("Grade cancelled. Nothing was saved.");
        }
        pause();
    }

    // ---------------- View Grade Report ----------------
    private static void viewGradeReport() {
        System.out.println("VIEW GRADE REPORT");
        System.out.println("---------------------------------------------");

        String id = readText("Enter Student ID: ");
        Student student = studentManager.findStudent(id);
        if (student == null) {
            System.out.println("Student not found: " + id);
            pause();
            return;
        }

        String sid = student.getStudentId();
        System.out.println();
        System.out.println("Student: " + sid + " - " + student.getName());
        System.out.println("Type: " + student.getStudentType() + " Student");
        System.out.println("Passing Grade: " + (int) student.getPassingGrade() + "%");

        if (!student.hasGrades()) {
            System.out.println("---------------------------------------------");
            System.out.println("No grades recorded for this student.");
            System.out.println("---------------------------------------------");
            pause();
            return;
        }

        System.out.println();
        gradeManager.viewGradesByStudent(sid);

        System.out.println();
        System.out.println("Total Grades: " + gradeManager.countGrades(sid));
        if (gradeManager.countGrades(sid, "Core") > 0) {
            System.out.printf("Core Subjects Average: %.1f%%%n", gradeManager.calculateCoreAverage(sid));
        } else {
            System.out.println("Core Subjects Average: N/A");
        }
        if (gradeManager.countGrades(sid, "Elective") > 0) {
            System.out.printf("Elective Subjects Average: %.1f%%%n", gradeManager.calculateElectiveAverage(sid));
        } else {
            System.out.println("Elective Subjects Average: N/A");
        }
        System.out.printf("Overall Average: %.1f%%%n", gradeManager.calculateOverallAverage(sid));

        System.out.println();
        if (student.isPassing()) {
            System.out.println("Performance Summary: PASSING");
        } else {
            System.out.println("Performance Summary: FAILING");
        }
        pause();
    }

    // ---------------- Input helpers: keep asking until the input is valid ----------------
    private static void pause() {
        System.out.println();
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    private static String readText(String prompt) {
        String text = "";
        while (text.isEmpty()) {
            System.out.print(prompt);
            text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println("This cannot be empty. Try again.");
            }
        }
        return text;
    }

    private static String readEmail(String prompt) {
        String email = readText(prompt);
        while (!email.contains("@") || !email.contains(".")) {
            System.out.println("Please enter a valid email (example: name@school.edu).");
            email = readText(prompt);
        }
        return email;
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            String text = readText(prompt);
            try {
                int number = Integer.parseInt(text);   // crashes on "abc", so we catch it
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // not a number: fall through to the message
            }
            System.out.println("Please enter a whole number from " + min + " to " + max + ".");
        }
    }

    private static double readGrade(String prompt) {
        while (true) {
            String text = readText(prompt);
            try {
                double number = Double.parseDouble(text);
                if (number >= 0 && number <= 100) {
                    return number;
                }
                System.out.println("Grade must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number, for example 75.");
            }
        }
    }
}
