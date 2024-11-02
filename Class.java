import java.util.Random;

public class Class {

    private String time;
    private String professorDifficulty;
    private Professor professor; // Association with Professor

    // Constructor to initialize the Class with a Professor instance
    public Class() {
        this.professor = new Professor(); // Assigns a new Professor
        this.professorDifficulty = professor.randomizeDifficulty(); // Sets the professor's difficulty
        this.time = randomizeClassTime(); // Sets the class time
    }

    // Method to randomize class time (8 AM or Normal)
    private String randomizeClassTime() {
        return new Random().nextInt(2) == 0 ? "8 AM" : "Normal";
    }

    // Assigns and returns the associated Professor
    public Professor assignProfessor() {
        return this.professor;
    }

    // Displays information about the class
    public String displayClassInfo() {
        int extraCredit = professor.assignExtraCredit();
        return "Class Time: " + this.time +
               "\nProfessor Difficulty: " + this.professorDifficulty +
               "\nExtra Credit: " + extraCredit;
    }
}
