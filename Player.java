public class Player {
    private int credits;
    private int failedExams;

    // Constructor to initialize player with default values
    public Player() {
        this.credits = 0;
        this.failedExams = 0;
    }

    // Registers the player for a new class
    public Class registerClass() {
        System.out.println("Class registered.");
        return new Class(); 
    }

    // Checks and returns the player's current credits
    public int checkCredits() {
        System.out.println("You currently have " + credits + " credits.");
        return credits;
    }

    // Allows the player to drop a class
    public boolean dropClass() {
        // Example logic to drop a class
        System.out.println("Class dropped.");
        return true; // Return true if successful
    }

    // Allows the player to take an exam
    public Exam takeExam() {
        System.out.println("Starting exam.");
        Exam exam = new Exam(/* Pass questions and timer values if needed */);
        exam.startExam();
        return exam;
    }

    // Increment credits (could be called when passing an exam)
    public void addCredits(int points) {
        credits += points;
    }

    // Increment failed exams (could be called when failing an exam)
    public void incrementFailedExams() {
        failedExams++;
    }

    // Getter for failed exams
    public int getFailedExams() {
        return failedExams;
    }
}
