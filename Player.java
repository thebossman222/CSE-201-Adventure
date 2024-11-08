public class Player {
    private int credits;
    private int failedExams;
    private boolean canDrop;
    private boolean canRetake;

    // Constructor to initialize player with default values
    public Player() {
        this.credits = 0;
        this.failedExams = 0;
        this.canDrop = true; // Player can drop a class initially
        this.canRetake = true; // Player can retake an exam initially
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
    
    // Checks if the player can drop a class
    public boolean canDropClass() {
        return canDrop;
    }

    // Allows the player to drop a class if possible
    public boolean dropClass() {
        if (canDrop) {
            canDrop = false; // Set canDrop to false once used
            System.out.println("Class dropped.");
            return true;
        } else {
            System.out.println("You have already used your drop option.");
            return false;
        }
    }

    // Allows the player to take an exam
    public Exam takeExam() {
        System.out.println("Starting exam.");
        Exam exam = new Exam();
        exam.startExam();
        return exam;
    }

    // Allows the player to retake an exam if possible
    public boolean retakeExam() {
        if (canRetake) {
            canRetake = false; // Set canRetake to false once used
            System.out.println("Retaking the exam...");
            Exam exam = new Exam();
            exam.startExam();
            return true;
        } else {
            System.out.println("You have already used your retake option.");
            return false;
        }
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
