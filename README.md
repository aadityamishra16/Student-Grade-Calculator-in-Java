# 🎓 Student Grade Calculator in Java

A desktop student grade management system built with Java Swing. It lets you add students, record their subject-wise marks, and instantly calculates their average, letter grade, and top scorers — all through a clean, warm-themed GUI.

## ✨ Features

- Add students with their name, ID, and subject grades
- Automatically calculates the average and assigns a letter grade (A–F)
- View full details of any single student by entering their Student ID
- View a formatted list of all students at once
- Remove a student from the system by ID
- Top Scorers view — shows the top 3 performers in each subject
- Clear form fields with one click
- Grade validation (marks must be between 0–100)
- Simple, readable output panel styled like a report card

## ⚙️ How It Works

The project follows a clean, object-oriented structure split across five files:

| File | Responsibility |
|---|---|
| `Main.java` | Entry point. Launches the GUI. |
| `GradeManagerGUI.java` | The Swing interface — input fields, buttons, and the results display panel. |
| `GradeManager.java` | Core logic — manages the student list, calculates averages/grades, and generates formatted reports. |
| `GradeCalculator.java` | Interface defining how averages and grades are calculated. |
| `Student.java` | Represents a single student — name, ID, and a map of subject-to-grade values. |

`GradeManager` implements the `GradeCalculator` interface, keeping the grading logic decoupled from the GUI and the student data model — a simple demonstration of interface-based design in Java.

## 📚 Subjects Tracked

Each student's marks are recorded across five subjects:

```
AMT-1, ADSA, DBMS, AT, OE
```

## 🧮 Grading Scale

| Average | Grade |
|---|---|
| 90 and above | A |
| 80 – 89 | B |
| 70 – 79 | C |
| 60 – 69 | D |
| Below 60 | F |

## 📋 Prerequisites

- **Java Development Kit (JDK) 8 or higher** installed and added to your system `PATH`

Check your Java installation:

```bash
java -version
javac -version
```

## 🚀 Setup

Clone or download this repository:

```bash
git clone https://github.com/aadityamishra16/Student-Grade-Calculator-in-Java.git
cd Student-Grade-Calculator-in-Java
```

## ▶️ Running the App

Compile all the Java files:

```bash
javac *.java
```

Run the application:

```bash
java Main
```

The GUI window should open automatically.

## 🖱️ Usage

1. Enter the **Student Name** and **Student ID**.
2. Fill in marks (0–100) for one or more subjects — you don't need to fill in every subject.
3. Click **Add Student** to save the record. The average, grade, and full report will appear in the display panel.
4. Click **View All** to see every student currently in the system.
5. Click **Find Student** and enter an ID to look up a specific student's report.
6. Click **Remove Student** and enter an ID to delete a record.
7. Click **Top Scorers** to see the top 3 performers in each subject.
8. Click **Clear** to reset the input fields, or **Exit** to close the app.

## 📝 Notes

- All student data is stored in memory only — records are lost when the app is closed. There's no file or database persistence yet.
- At least one subject grade is required to add a student.

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
