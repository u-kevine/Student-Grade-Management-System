// ABSTRACT parent of RegularStudent and HonorsStudent.
public abstract class Student {

    private static int studentCounter = 0;   // shared counter for STU001, STU002 ...

    private String studentId;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String status;

    // A student does not store its grades. It asks the GradeManager for them.
    private GradeManager gradeManager;

    public Student(String name, int age, String email, String phone) {
        studentCounter++;
        this.studentId = String.format("STU%03d", studentCounter);
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.status = "Active";
    }

    // Children MUST write these three:
    public abstract void displayStudentDetails();
    public abstract String getStudentType();
    public abstract double getPassingGrade();

    public double calculateAverageGrade() {
        return gradeManager.calculateOverallAverage(studentId);
    }

    public boolean hasGrades() {
        return gradeManager.countGrades(studentId) > 0;
    }

    // Passing = has at least one grade AND average >= passing grade
    public boolean isPassing() {
        return hasGrades() && calculateAverageGrade() >= getPassingGrade();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public void setGradeManager(GradeManager gradeManager) { this.gradeManager = gradeManager; }
}
