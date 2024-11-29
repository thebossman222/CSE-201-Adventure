import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;

public class Exam {
    private List<Question> questions;
    private int score;

    // Constructor to initialize exam questions and timer
    public Exam(List<Question> questions, int countdownTimer) {
        this.questions = questions;
        this.score = 0;
    }

    public Exam() {

    }

    public boolean startExam(Course currentCourse, Player player) {

        Professor professor = currentCourse.getProfessor();
        this.score = professor.getExtraCredit();

        if (questions == null || questions.isEmpty()) {
            // Fetch questions from the course
            Quizzes quizzes = new Quizzes();
            String filePath = currentCourse.file();
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Error: File not found at " + filePath);
                return false;
            }

            Map<String, String> questionsMap = quizzes.readQuestions(filePath);
            questions = quizzes.questionsWithChoices(questionsMap);
        }

        // Set the total time limit for the exam (e.g., 120 seconds)
        int time = 120;

        System.out.println("Exam started. You have " + time
                + " seconds to complete the exam.");

        // Limit questions to 10
        if (questions.size() > 10) {
            questions = questions.subList(0, 10);
        }

        if (currentCourse.getProfessorDifficulty().equalsIgnoreCase("Hard")) {
            // Take the first exam
            System.out.println("Starting first exam...");
            int firstExamScore = conductExam(questions, time);
            System.out.println("You scored " + firstExamScore + " out of "
                    + questions.size() + " on the first exam.");

            // Option to drop the class or continue
            Scanner scanner = new Scanner(System.in);
            System.out.println("You have completed the first exam.");
            System.out.println(
                    " Do you want to drop the class or continue to the next exam? \n"
                            + " (Type 'drop' to drop or 'continue' to proceed)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("drop")) {
                System.out.println("Class dropped.");
                player.dropCourse(currentCourse); // Drop the current course
                return false;
            }

            // Take the second exam
            System.out.println("Starting second exam...");
            int secondExamScore = conductExam(questions, time);

            System.out.println("You scored " + secondExamScore + " out of "
                    + questions.size() + " on the second exam. \n");

            // Calculate the average score of the two exams
            double averageScore = (firstExamScore + secondExamScore) / 2.0;

            this.score = (int) averageScore;

            System.out.println("You total score :" + this.score);

            // Check if passed both exams
            if (firstExamScore >= 6 && this.score >= 6) {
                System.out.println("You have passed the Hard class!");
                currentCourse.setPassed(true); // Mark the course as passed
                return true;
            } else {
                System.out.println("You have failed the Hard class.");
                player.incrementFailedExams();
                return false;
            }
        } else {

            // Take one exam for easy level
            int examScore = conductExam(questions, time);
            System.out.println("You scored " + examScore + " out of "
                    + questions.size() + " on the exam.");

            // Check if passed
            if (examScore >= 6) {
                System.out.println("You have passed the Easy class!");
                currentCourse.setPassed(true); // Mark the course as passed
                return true;
            } else {
                System.out.println("You have failed the Easy class.");
                player.incrementFailedExams();
                return false;
            }
        }
    }

    // Method to conduct the exam with countdown timer
    private int conductExam(List<Question> questionsWithChoices,
            int timeLimitInSeconds) {
        Scanner scanner = new Scanner(System.in);
        int examScore = 0;

        // Shuffle the questions to randomize them
        Collections.shuffle(questionsWithChoices);

        long examStartTime = System.currentTimeMillis();

        for (int i = 0; i < questionsWithChoices.size(); i++) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - examStartTime) / 1000; // in
                                                                     // seconds
            long timeRemaining = timeLimitInSeconds - elapsedTime;

            if (timeRemaining <= 0) {
                System.out.println("\nTime is up! The exam has ended.");
                break;
            }

            System.out
                    .println("\nTime remaining: " + timeRemaining
                            + " seconds \n");

            Question question = questionsWithChoices.get(i);
            List<String> choices = question.getChoices();

            // Shuffle the choices to randomize them
            Collections.shuffle(choices);

            // Display the question
            System.out.println(
                    "Question " + (i + 1) + ": " + question.getQuestionText()
                            + "\n");

            // Display the choices
            for (int j = 0; j < choices.size(); j++) {
                System.out.println((char) ('A' + j) + ") " + choices.get(j));
            }

            // Map letters to choices
            char correctLetter = ' ';
            for (int j = 0; j < choices.size(); j++) {
                if (choices.get(j).equals(question.getCorrectAnswer())) {
                    correctLetter = (char) ('A' + j);
                    break;
                }
            }

            // Prompt user for their answer
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().trim().toUpperCase();

            if (userAnswer.length() == 1 && userAnswer.charAt(0) >= 'A'
                    && userAnswer.charAt(0) <= 'D') {
                if (userAnswer.charAt(0) == correctLetter) {
                    System.out.println("Correct!");
                    examScore++;
                } else {
                    System.out.println("Incorrect. The correct answer was "
                            + correctLetter + ".");
                }
            } else if (userAnswer.equalsIgnoreCase("Messi")
                    || userAnswer.equalsIgnoreCase("Mohamed")) {
                System.out.println("Correct!"); // "Messi" and "Mohamed" is
                                                // always considered correct
                examScore++;
            } else {
                System.out
                        .println("Invalid answer. Please enter A, B, C, or D.");

            }
        }

        return examScore;
    }

}
