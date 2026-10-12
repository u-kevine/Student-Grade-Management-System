// ABSTRACT class
// CoreSubject and ElectiveSubject finish it.
public abstract class Subject {

    // private = only this class can use these directly (ENCAPSULATION)
    private String subjectName;
    private String subjectCode;

    public Subject(String subjectName, String subjectCode) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
    }

    // getters and setters = the public doors to the private fields
    public String getSubjectName() { return subjectName; }
    public String getSubjectCode() { return subjectCode; }


    // abstract methods have no body bcz every child MUST write its own version
    public abstract void displaySubjectDetails();
    public abstract String getSubjectType();
}
