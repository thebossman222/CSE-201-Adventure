import java.util.List;

public class Exam {
    private List<String> questions; 
    private int countdownTimer;
    private int score;

    // Constructor to initialize exam questions and timer
    public Exam(List<String> questions, int countdownTimer) {
        this.questions = questions;
        this.countdownTimer = countdownTimer;
        this.score = 0; 
    }

    // Starts the exam
    public boolean startExam() {
        if (questions.isEmpty()) {
            System.out.println("No questions available for the exam.");
            return false;
        }
        System.out.println("Exam started with " + countdownTimer + " seconds.");
        // Additional logic to start countdown can go here
        return true;
    }

    // Submits the exam and finalizes the score
    public boolean submitExam() {
        if (countdownTimer > 0) {
            System.out.println("Exam submitted.");
            calculateScore(); // Calculate final score
            return true;
        } else {
            System.out.println("Time is up! Exam automatically submitted.");
            calculateScore();
            return false;
        }
    }

    // Calculates the score
    public int calculateScore() {
        // Example scoring logic: each correct answer adds 1 point to score
        // Here, score calculation logic will depend on your question structure
        this.score = questions.size(); // Dummy calculation for simplicity
        System.out.println("Your final score is: " + score);
        return this.score;
    }

    // Getter for score (optional)
    public int getScore() {
        return this.score;
    }
}
