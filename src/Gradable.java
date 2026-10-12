// An INTERFACE is a list of methods a class promises to have.
public interface Gradable {
    boolean recordGrade(double grade);    // save the grade; true if it was valid
    boolean validateGrade(double grade);  // true if the grade is between 0 and 100
}
