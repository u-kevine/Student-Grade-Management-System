public class ElectiveSubject extends Subject {

    private boolean mandatory;

    public ElectiveSubject(String subjectName, String subjectCode) {
        super(subjectName, subjectCode);
        this.mandatory = false;
    }

    public boolean isMandatory() { return mandatory; }

    @Override
    public void displaySubjectDetails() {
        System.out.println(getSubjectName() + " (" + getSubjectCode() + ") - Elective, optional");
    }

    @Override
    public String getSubjectType() { return "Elective"; }
}
