import java.util.Random;

/**
 * Represents a class with a specific time, associated professor,
 * and a difficulty level determined by the professor.
 */
public class Class {

    private String time;
    private String professorDifficulty;
    private Professor professor; // Association with Professor

    /**
     * Constructor to initialize the Class with a new Professor instance.
     * Randomize the difficulty level of the professor (easy or hard) and the class time (8 AM or normal).
     */
    public Class() {
        this.professor = new Professor(); // Assigns a new Professor
        this.professorDifficulty = professor.randomizeDifficulty(); // Sets the professor's difficulty
        this.time = randomizeClassTime(); // Sets the class time
    }

    /**
     * Randomizes the class time to either "8 AM" or "Normal".
     * 
     * @return A string representing the randomized class time.
     */
    private String randomizeClassTime() {
        return new Random().nextInt(2) == 0 ? "8 AM" : "Normal";
    }

    /**
     * Assigns and returns the associated Professor.
     * 
     * @return The professor assigned to the class.
     */
    public Professor assignProfessor() {
        return this.professor;
    }

    /**
     * Displays information about the class including the class time,
     * professor difficulty, and extra credit or deduction points.
     * 
     * @return A string containing details about the class.
     */
    public String displayClassInfo() {
        int extraCredit = professor.assignExtraCredit();
        return "Class Time: " + this.time +
               "\nProfessor Difficulty: " + this.professorDifficulty +
               "\nExtra Credit: " + extraCredit;
    }
}
