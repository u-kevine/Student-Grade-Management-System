import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// "implements Gradable" = this class keeps the promise to have
// recordGrade() and validateGrade().
public class Grade implements Gradable {

    // static = ONE copy shared by every Grade object. We use it to count grades.
    private static int gradeCounter = 0;

    private String gradeId;
    private String studentId;
    private Subject subject;   // a Grade HAS A Subject (composition)
    private double grade;
    private String date;

    public Grade(String studentId, Subject subject, double grade) {
        gradeCounter++;                                         // 1, 2, 3 ...
        this.gradeId = String.format("GRD%03d", gradeCounter);  // GRD001, GRD002 ...
        this.studentId = studentId;
        this.subject = subject;
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        recordGrade(grade);
    }

    @Override
    public boolean recordGrade(double grade) {
        if (validateGrade(grade)) {
            this.grade = grade;
            return true;
        }
        return false;
    }

    @Override
    public boolean validateGrade(double grade) {
        return grade >= 0 && grade <= 100;
    }

    // A = 90+, B = 80+, C = 70+, D = 60+, otherwise F
    public String getLetterGrade() {
        if (grade >= 90) {
            return "A";
        } else if (grade >= 80) {
            return "B";
        } else if (grade >= 70) {
            return "C";
        } else if (grade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public void displayGradeDetails() {
        System.out.println("Grade ID: " + gradeId);
        System.out.println("Student: " + studentId);
        System.out.println("Subject: " + subject.getSubjectName() + " (" + subject.getSubjectType() + ")");
        System.out.println("Grade: " + grade + "% (" + getLetterGrade() + ")");
        System.out.println("Date: " + date);
    }

    public String getGradeId() { return gradeId; }
    public String getStudentId() { return studentId; }
    public Subject getSubject() { return subject; }
    public double getGrade() { return grade; }
    public String getDate() { return date; }
}
