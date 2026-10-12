// "extends" = INHERITANCE: CoreSubject gets everything Subject has.
public class CoreSubject extends Subject {

    private boolean mandatory;

    public CoreSubject(String subjectName, String subjectCode) {
        super(subjectName, subjectCode);  // run the parent's constructor
        this.mandatory = true;
    }

    public boolean isMandatory() { return mandatory; }

    @Override  // "I am replacing the parent's method"
    public void displaySubjectDetails() {
        System.out.println(getSubjectName() + " (" + getSubjectCode() + ") - Core, mandatory");
    }

    @Override
    public String getSubjectType() { return "Core"; }
}
