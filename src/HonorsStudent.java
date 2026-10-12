public class HonorsStudent extends Student {

    private double passingGrade;
    private boolean honorsEligible;

    public HonorsStudent(String name, int age, String email, String phone) {
        super(name, age, email, phone);
        this.passingGrade = 60.0;
        this.honorsEligible = false;
    }

    // Eligible when the average is 85 or more
    public boolean checkHonorsEligibility() {
        honorsEligible = hasGrades() && calculateAverageGrade() >= 85;
        return honorsEligible;
    }

    @Override
    public void displayStudentDetails() {
        System.out.println("  Student ID: " + getStudentId());
        System.out.println("  Name: " + getName());
        System.out.println("  Type: " + getStudentType());
        System.out.println("  Age: " + getAge());
        System.out.println("  Email: " + getEmail());
        System.out.println("  Passing Grade: " + (int) getPassingGrade() + "%");
        if (checkHonorsEligibility()) {
            System.out.println("  Honors Eligible: Yes");
        } else {
            System.out.println("  Honors Eligible: No");
        }
        System.out.println("  Status: " + getStatus());
    }

    @Override
    public String getStudentType() { return "Honors"; }

    @Override
    public double getPassingGrade() { return passingGrade; }
}
