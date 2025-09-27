import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class GradeManagerGUI extends JFrame {
    private final GradeManager gradeManager;
    private JTextArea displayArea;
    private JTextField nameField, idField;
    private JTextField[] gradeFields;
    
    public GradeManagerGUI() {
        gradeManager = new GradeManager();
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Student Grade Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800); // Increased height for better display
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Header
        JLabel headerLabel = new JLabel("STUDENT GRADE MANAGEMENT SYSTEM", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(new Color(0, 70, 140));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        // Create left panel for inputs and buttons
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setBackground(new Color(240, 240, 240));
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        inputPanel.setPreferredSize(new Dimension(400, 300)); // Fixed size for inputs
        
        inputPanel.add(createStyledLabel("Student Name:"));
        nameField = createStyledTextField();
        inputPanel.add(nameField);
        
        inputPanel.add(createStyledLabel("Student ID:"));
        idField = createStyledTextField();
        inputPanel.add(idField);
        
        // Subject grades
        String[] subjects = gradeManager.getSubjects();
        gradeFields = new JTextField[subjects.length];
        
        for (int i = 0; i < subjects.length; i++) {
            inputPanel.add(createStyledLabel(subjects[i] + ":"));
            gradeFields[i] = createStyledTextField();
            inputPanel.add(gradeFields[i]);
        }
        
        // Button panel - made smaller
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonPanel.setPreferredSize(new Dimension(400, 150)); // Smaller fixed size
        
        JButton addButton = createStyledButton("Add Student");
        JButton viewButton = createStyledButton("View All");
        JButton findButton = createStyledButton("Find Student");
        JButton removeButton = createStyledButton("Remove Student");
        JButton clearButton = createStyledButton("Clear");
        JButton exitButton = createStyledButton("Exit");
        
        addButton.addActionListener(this::addStudent);
        viewButton.addActionListener(this::viewAllStudents);
        findButton.addActionListener(this::findStudent);
        removeButton.addActionListener(this::removeStudent);
        clearButton.addActionListener(e -> clearFields());
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(findButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        
        // Add input and button panels to left panel
        leftPanel.add(inputPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Display area - made larger
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(240, 240, 240));
        displayPanel.setBorder(BorderFactory.createTitledBorder("Student Records"));
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setBackground(new Color(255, 255, 225));
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scrollPane.setPreferredSize(new Dimension(450, 600)); // Larger display area
        
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add components to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Center panel with left and right sections
        JPanel centerPanel = new JPanel(new BorderLayout(20, 0));
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.add(leftPanel, BorderLayout.WEST);
        centerPanel.add(displayPanel, BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 11)); // Smaller font for buttons
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6)); // Smaller padding
        button.setMargin(new Insets(2, 2, 2, 2)); // Smaller margin
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 110, 160));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
        
        return button;
    }
    
    private void addStudent(ActionEvent e) {
        try {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            
            if (name.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter name and ID!");
                return;
            }
            
            Map<String, Double> subjectGrades = new HashMap<>();
            String[] subjects = gradeManager.getSubjects();
            
            for (int i = 0; i < subjects.length; i++) {
                String gradeText = gradeFields[i].getText().trim();
                if (!gradeText.isEmpty()) {
                    double grade = Double.parseDouble(gradeText);
                    if (grade < 0 || grade > 100) {
                        JOptionPane.showMessageDialog(this, "Grades must be between 0-100!");
                        return;
                    }
                    subjectGrades.put(subjects[i], grade);
                }
            }
            
            if (subjectGrades.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least one grade!");
                return;
            }
            
            Student student = new Student(name, id, subjectGrades);
            gradeManager.addStudent(student);
            
            displayArea.setText("Student added successfully!\n");
            displayArea.append(gradeManager.getStudentStatistics(student));
            clearFields();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for grades!");
        }
    }
    
    private void viewAllStudents(ActionEvent e) {
        java.util.List<Student> students = gradeManager.getAllStudents();
        
        if (students.isEmpty()) {
            displayArea.setText("No students found in the system!");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("══════════════════════════════════════════════════════════\n");
        sb.append("                       ALL STUDENTS                       \n");
        sb.append("══════════════════════════════════════════════════════════\n\n");
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            sb.append(gradeManager.getStudentStatistics(student)).append("\n\n");
        }
        
        sb.append("Total Students: ").append(students.size()).append("\n");
        displayArea.setText(sb.toString());
    }
    
    private void findStudent(ActionEvent e) {
        String id = JOptionPane.showInputDialog(this, "Enter Student ID to find:");
        
        if (id == null) {
            return;
        }
        
        id = id.trim();
        if (id.isEmpty()) {
            displayArea.setText("Please enter a valid Student ID!");
            return;
        }
        
        Student student = gradeManager.findStudent(id);
        if (student != null) {
            displayArea.setText(gradeManager.getStudentStatistics(student));
        } else {
            displayArea.setText("Student not found with ID: " + id);
        }
    }
    
    private void removeStudent(ActionEvent e) {
        String id = JOptionPane.showInputDialog(this, "Enter Student ID to remove:");
        
        if (id == null || id.trim().isEmpty()) {
            return;
        }
        
        id = id.trim();
        if (gradeManager.removeStudent(id)) {
            displayArea.setText("Student with ID " + id + " removed successfully!");
            clearFields();
        } else {
            displayArea.setText("Student not found with ID: " + id);
        }
    }
    
    private void clearFields() {
        nameField.setText("");
        idField.setText("");
        for (JTextField field : gradeFields) {
            field.setText("");
        }
    }
}