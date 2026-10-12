public class RegularStudent extends Student {

    private final double passingGrade;

    public RegularStudent(String name, int age, String email, String phone) {
        super(name, age, email, phone);
        this.passingGrade = 50.0;
    }

    @Override
    public void displayStudentDetails() {
        System.out.println("  Student ID: " + getStudentId());
        System.out.println("  Name: " + getName());
        System.out.println("  Type: " + getStudentType());
        System.out.println("  Age: " + getAge());
        System.out.println("  Email: " + getEmail());
        System.out.println("  Passing Grade: " + (int) getPassingGrade() + "%");
        System.out.println("  Status: " + getStatus());
    }

    @Override
    public String getStudentType() { return "Regular"; }

    @Override
    public double getPassingGrade() { return passingGrade; }
}
