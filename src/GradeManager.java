// Keeps all grades in an ARRAY. The array is private (encapsulation);
public class GradeManager {

    private Grade[] grades = new Grade[200];
    private int gradeCount = 0;   // how many slots of the array are really used

    public void addGrade(Grade grade) {
        grades[gradeCount] = grade;   // put it in the next free slot
        gradeCount++;
    }

    // Shows one student's grades, NEWEST FIRST.
    public void viewGradesByStudent(String studentId) {
        System.out.println("GRADE HISTORY");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-7s | %-10s | %-18s | %-9s | %s%n", "GRD ID", "DATE", "SUBJECT", "TYPE", "GRADE");
        System.out.println("---------------------------------------------------------------------");
        for (int i = gradeCount - 1; i >= 0; i--) {
            Grade g = grades[i];
            if (g.getStudentId().equals(studentId)) {   // only this student's grades
                System.out.printf("%-7s | %-10s | %-18s | %-9s | %.1f%%%n",
                        g.getGradeId(), g.getDate(), g.getSubject().getSubjectName(),
                        g.getSubject().getSubjectType(), g.getGrade());
            }
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public double calculateCoreAverage(String studentId) {
        return calculateAverage(studentId, "Core");
    }

    public double calculateElectiveAverage(String studentId) {
        return calculateAverage(studentId, "Elective");
    }

    public double calculateOverallAverage(String studentId) {
        return calculateAverage(studentId, "All");
    }

    public int getGradeCount() { return gradeCount; }

    // Counts one student's grades. Two methods with the same name but
    // different parameters = OVERLOADING.
    public int countGrades(String studentId) {
        return countGrades(studentId, "All");
    }

    public int countGrades(String studentId, String type) {
        int count = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (matches(grades[i], studentId, type)) {
                count++;
            }
        }
        return count;
    }

    // Add up the matching grades, then divide by how many there were.
    private double calculateAverage(String studentId, String type) {
        double total = 0;
        int count = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (matches(grades[i], studentId, type)) {
                total = total + grades[i].getGrade();
                count++;
            }
        }
        if (count == 0) {
            return 0;     // avoid dividing by zero
        }
        return total / count;
    }

    // Does this grade belong to the student and to the wanted type?
    private boolean matches(Grade g, String studentId, String type) {
        if (!g.getStudentId().equals(studentId)) {
            return false;
        }
        return type.equals("All") || g.getSubject().getSubjectType().equals(type);
    }
}