public class Class {

    private String time;
    private Professor professor; // Composition: Class has a Professor

    // Constructor to initialize class with a randomized professor and class time
    public Class() {
        this.professor = new Professor(); // Create a new Professor instance
        this.professor.randomizeDifficulty(); // Randomize professor difficulty
        this.professor.assignExtraCredit(); // Assign extra credit based on difficulty
        this.time = randomizeClassTime(); // Randomize class time (8 AM or Normal)
    }

    // Method to randomize class time
    private String randomizeClassTime() {
        return new Random().nextInt(2) == 0 ? "8 AM" : "Normal";
    }

    // Method to return professor information
    public Professor getProfessor() {
        return this.professor;
    }

    // Method to display class info
    public String displayClassInfo() {
        return "Class Time: " + this.time +
               "\nProfessor Difficulty: " + this.professor.randomizeDifficulty() +
               "\nExtra Credit: " + this.professor.assignExtraCredit();
    }
}
