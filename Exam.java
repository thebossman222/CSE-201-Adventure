import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Exam {
    private List<Question> questions;
    private int countdownTimer;
    private int score;

    // Constructor to initialize exam questions and timer
    public Exam(List<Question> questions, int countdownTimer) {
        this.questions = questions;
        this.countdownTimer = countdownTimer;
        this.score = 0;
    }

    public Exam() {
    }

    // Starts the exam
    public boolean startExam() {
        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions available for the exam.");
            return false;
        }
        System.out.println("Exam started with " + countdownTimer + " seconds.");
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> choices = question.getChoices();
            for (int j = 0; j < choices.size(); j++) {
                System.out.println((char) ('A' + j) + ") " + choices.get(j));
            }
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
            }
        }
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
        // Score has already been calculated during the exam
        System.out.println("Your final score is: " + score);
        return this.score;
    }

    // Getter for the score
    public int getScore() {
        return this.score;
    }

    // Getter for the questions
    public List<Question> getQuestions() {
        return this.questions;
    }

    // Takes the exam process considering the difficulty level of the class
    public boolean takeClassExam(Class currentClass, Quizzes quizzes,
            String filePath) {
        // Get questions from Quizzes class
        Map<String, String> questionsMap = quizzes
                .readQuestionsFromFile(filePath);
        this.questions = quizzes.generateQuestionsWithChoices(questionsMap);
        Scanner scanner = new Scanner(System.in);
        if (currentClass.getProfessorDifficulty().equalsIgnoreCase("Hard")) {
            System.out.println(
                    "This is a Hard level class. You need to take two exams.");
            // Take the first exam
            Exam firstExam = new Exam(this.questions, 60);
            firstExam.startExam();
            firstExam.submitExam();

            // Option to drop the class or continue
            System.out.println(
                    "You have completed the first exam. Do you want to drop the class "
                    + "or continue to the next exam? (Type 'drop' to drop or 'continue' to proceed)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("drop")) {
                System.out.println("Class dropped.");
                return false;
            }

            // Take the second exam
            Exam secondExam = new Exam(this.questions, 60);
            secondExam.startExam();
            secondExam.submitExam();

            // Check if passed both exams
            if (firstExam.getScore() >= 6 && secondExam.getScore() >= 6) {
                System.out.println("You have passed the Hard class!");
                return true;
            } else {
                System.out.println("You have failed the Hard class.");
                return false;
            }
        } else {
            System.out.println(
                    "This is an Easy level class. You need to take one exam.");
            // Take one exam for easy level
            Exam easyExam = new Exam(this.questions, 60);
            easyExam.startExam();
            easyExam.submitExam();

            if (easyExam.getScore() >= 6) {
                System.out.println("You have passed the Easy class!");
                return true;
            } else {
                System.out.println("You have failed the Easy class.");
                return false;
            }
        }
    }
}
