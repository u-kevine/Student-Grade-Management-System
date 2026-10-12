// It can hold RegularStudent AND HonorsStudent objects (POLYMORPHISM)
public class StudentManager {

    private Student[] students = new Student[50];
    private int studentCount = 0;
    private GradeManager gradeManager;

    public StudentManager(GradeManager gradeManager) {
        this.gradeManager = gradeManager;
    }

    public void addStudent(Student student) {
        student.setGradeManager(gradeManager);   // connect the student to the grades
        students[studentCount] = student;
        studentCount++;
    }

    // Linear search: look at each student until the ID matches.
    public Student findStudent(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(studentId)) {
                return students[i];
            }
        }
        return null;   // not found
    }

    public void viewAllStudents() {
        System.out.println("STUDENT LISTING");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-8s | %-17s | %-8s | %-9s | %s%n", "STU ID", "NAME", "TYPE", "AVG GRADE", "STATUS");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < studentCount; i++) {
            Student s = students[i];

            String average = "N/A";
            String status = "No Grades";
            if (s.hasGrades()) {
                average = String.format("%.1f%%", s.calculateAverageGrade());
                if (s.isPassing()) {
                    status = "Passing";
                } else {
                    status = "Failing";
                }
            }

            System.out.printf("%-8s | %-17s | %-8s | %-9s | %s%n",
                    s.getStudentId(), s.getName(), s.getStudentType(), average, status);

            String line = "         | Grades Recorded: " + gradeManager.countGrades(s.getStudentId())
                    + " | Passing Grade: " + (int) s.getPassingGrade() + "%";

            // instanceof asks: "is this Student really an HonorsStudent?"
            if (s instanceof HonorsStudent) {
                HonorsStudent honors = (HonorsStudent) s;
                if (honors.checkHonorsEligibility()) {
                    line = line + " | Honors Eligible";
                }
            }
            System.out.println(line);
            System.out.println("--------------------------------------------------------------------------");
        }

        System.out.println();
        System.out.println("Total Students: " + studentCount);
        System.out.printf("Average Class Grade: %.1f%%%n", getAverageClassGrade());
    }

    // Average of the students' averages (students without grades are skipped).
    public double getAverageClassGrade() {
        double total = 0;
        int counted = 0;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].hasGrades()) {
                total = total + students[i].calculateAverageGrade();
                counted++;
            }
        }
        if (counted == 0) {
            return 0;
        }
        return total / counted;
    }

    public int getStudentCount() { return studentCount; }
}
