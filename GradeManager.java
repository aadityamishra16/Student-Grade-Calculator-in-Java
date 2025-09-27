import java.util.*;

public class GradeManager implements GradeCalculator {
    private final List<Student> students;
    private final String[] SUBJECTS = {"AMT-1", "ADSA", "DBMS", "AT", "OE"};
    
    public GradeManager() {
        students = new ArrayList<>();
    }
    
    // Add a student
    public void addStudent(Student student) {
        students.add(student);
    }
    
    // Remove a student
    public boolean removeStudent(String id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
    
    // Get all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    // Find student by ID
    public Student findStudent(String id) {
        if (id == null) return null;
        
        String searchId = id.trim(); // Remove whitespace
        for (Student student : students) {
            if (student.getId() != null && student.getId().trim().equals(searchId)) {
                return student;
            }
        }
        return null;
    }
    
    // Get available subjects
    public String[] getSubjects() {
        return SUBJECTS;
    }
    
    // Implement interface methods
    @Override
    public double calculateAverage(Map<String, Double> subjectGrades) {
        if (subjectGrades.isEmpty()) return 0;
        
        double sum = 0;
        int count = 0;
        
        for (Double grade : subjectGrades.values()) {
            if (grade != null) {
                sum += grade;
                count++;
            }
        }
        
        return count > 0 ? sum / count : 0;
    }
    
    @Override
    public String calculateGrade(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
    
    // Get student statistics - REMOVED Pointer display
    public String getStudentStatistics(Student student) {
        double average = calculateAverage(student.getSubjectGrades());
        String grade = calculateGrade(average);
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append(String.format("STUDENT DETAILS\n"));
        sb.append("═══════════════════════════════════════════\n");
        sb.append(String.format("Name: %s\n", student.getName()));
        sb.append(String.format("ID: %s\n", student.getId()));
        sb.append("\n");
        sb.append("SUBJECT GRADES:\n");
        sb.append("───────────────────────────────────────────\n");
        
        for (String subject : SUBJECTS) {
            Double subjectGrade = student.getGrade(subject);
            String gradeText = (subjectGrade != null) ? String.format("%.2f", subjectGrade) : "Not entered";
            sb.append(String.format("• %-8s: %s\n", subject, gradeText));
        }
        
        sb.append("\n");
        sb.append("OVERALL PERFORMANCE:\n");
        sb.append("───────────────────────────────────────────\n");
        sb.append(String.format("Average: %.2f\n", average));
        sb.append(String.format("Grade: %s\n", grade));
        sb.append("═══════════════════════════════════════════\n");
        
        return sb.toString();
    }
    
    // New method to get all students formatted - Using simple ASCII characters
    public String getAllStudentsFormatted() {
        if (students.isEmpty()) {
            return "No students found in the system!";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("==========================================================\n");
        sb.append("                       ALL STUDENTS                       \n");
        sb.append("==========================================================\n\n");
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            double average = calculateAverage(student.getSubjectGrades());
            String grade = calculateGrade(average);
            
            sb.append(String.format("STUDENT %d:\n", i + 1));
            sb.append("------------------------------------------\n");
            sb.append(String.format("Name: %s\n", student.getName()));
            sb.append(String.format("ID: %s\n", student.getId()));
            sb.append(String.format("Average: %.2f\n", average));
            sb.append(String.format("Grade: %s\n", grade));
            
            // Show subjects with grades
            sb.append("Subjects: ");
            boolean first = true;
            for (String subject : SUBJECTS) {
                Double subjectGrade = student.getGrade(subject);
                if (subjectGrade != null) {
                    if (!first) sb.append(", ");
                    sb.append(String.format("%s: %.2f", subject, subjectGrade));
                    first = false;
                }
            }
            sb.append("\n\n");
        }
        
        sb.append("==========================================================\n");
        sb.append(String.format("Total Students: %d\n", students.size()));
        sb.append("==========================================================\n");
        
        return sb.toString();
    }
}