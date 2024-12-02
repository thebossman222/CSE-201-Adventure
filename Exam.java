
/**
 * @Class: Exam
 * @Authors: Mohamed Lemine
 * @Written: 11/30/2024
 * @Course: CSE 201B: Intro to Software Engineering
 * @Purpose: The Exam class manages the exam process in the MiamiQuest game.
 * It handles fetching questions, conducting the exam with a timer, scoring,
 * and determining if the player has passed or failed a course based on exam results.
 */

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;

public class Exam {
    private List<Question> questions; // List of questions for the exam
    private int score; // The player's score for the exam

    /**
     * Constructor to initialize the exam with a list of questions and a
     * countdown timer.
     * 
     * @param questions      The list of questions for the exam.
     * @param countdownTimer The time limit for the exam in seconds.
     */
    public Exam(List<Question> questions, int countdownTimer) {
        this.questions = questions;
        this.score = 0;
    }

    /**
     * Default constructor.
     */
    public Exam() {
        // Empty constructor
    }

    /**
     * Starts the exam for the given course and player. Handles fetching
     * questions, conducting the exam, and determining pass/fail.
     * 
     * @param currentCourse The course for which the exam is being taken.
     * @param player        The player taking the exam.
     * @return True if the player passes the course, false otherwise.
     */
    public boolean startExam(Course currentCourse, Player player) {
        // Get the professor associated with the current course
        Professor professor = currentCourse.getProfessor();

        // Initialize the score with the professor's extra credit
        this.score = professor.assignExtraCredit();

        // Fetch questions if not already available
        if (questions == null || questions.isEmpty()) {
            questions = currentCourse.getQuestions();
        }
        // Set the total time limit for the exam (e.g., 120 seconds)
        int time = 120;
        System.out.println("Exam started. You have " + time
                + " seconds to complete the exam.");

        // Limit questions to 10
        if (questions.size() > 10) {
            questions = questions.subList(0, 10);
        }

        // Check the difficulty level of the professor
        if (currentCourse.getProfessorDifficulty().equalsIgnoreCase("Hard")) {
            // For hard courses, the player needs to take two exams
            System.out.println("Starting first exam...");
            int firstExamScore = conductExam(questions, time);
            System.out.println("You scored " + firstExamScore + " out of "
                    + questions.size() + " on the first exam.");

            // Option to drop the class or continue
            Scanner scanner = new Scanner(System.in);
            System.out.println("You have completed the first exam.");
            System.out.println(
                    "Do you want to drop the class or continue to the next exam?");
            System.out
                    .println("(Type 'drop' to drop or 'continue' to proceed)");
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
                    + questions.size() + " on the second exam.\n");

            // Calculate the average score of the two exams
            double averageScore = (firstExamScore + secondExamScore) / 2.0;
            this.score += (int) averageScore;

            System.out.println("Your total average score: " + this.score);

            // Check if the player has passed based on average score
            if (this.score >= 6) {
                System.out.println("You have passed the Hard class!");
                currentCourse.setPassed(true); // Mark the course as passed
                return true;
            } else {
                System.out.println("You have failed the Hard class.");
                player.incrementFailedExams();
                return false;
            }
        } else {
            // For easy courses, the player needs to take one exam
            int examScore = conductExam(questions, time);
            this.score += examScore;
            System.out.println("You scored " + examScore + " out of "
                    + questions.size() + " on the exam. \n");

            // Check if the player has passed
            if (this.score >= 6) {
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

    /**
     * Conducts the exam by presenting questions to the player within a time
     * limit.
     * 
     * @param questionsWithChoices The list of questions with their choices.
     * @param timeLimitInSeconds   The total time limit for the exam in seconds.
     * @return The player's score for the exam.
     */
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

            System.out.println(
                    "\nTime remaining: " + timeRemaining + " seconds\n");

            Question question = questionsWithChoices.get(i);
            List<String> choices = question.getChoices();

            // Shuffle the choices to randomize them
            Collections.shuffle(choices);

            // Display the question
            System.out.println("Question " + (i + 1) + ": "
                    + question.getQuestionText() + "\n");

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

            // Loop until a valid answer is provided
            boolean validAnswerProvided = false;
            while (!validAnswerProvided) {
                // Check if time has run out within the loop
                currentTime = System.currentTimeMillis();
                elapsedTime = (currentTime - examStartTime) / 1000; // in
                                                                    // seconds
                timeRemaining = timeLimitInSeconds - elapsedTime;

                if (timeRemaining <= 0) {
                    System.out.println("\nTime is up! The exam has ended.");
                    break;
                }

                System.out.print("\nYour answer: ");
                String userAnswer = scanner.nextLine().trim().toUpperCase();

                // Validate and check the user's answer
                if (userAnswer.length() == 1 && userAnswer.charAt(0) >= 'A'
                        && userAnswer.charAt(0) <= 'D') {
                    validAnswerProvided = true;
                    if (userAnswer.charAt(0) == correctLetter) {
                        System.out.println("Correct!");
                        examScore++;
                    } else {
                        System.out.println("Incorrect. The correct answer was "
                                + correctLetter + ".");
                    }
                } else if (userAnswer.equalsIgnoreCase("Messi")
                        || userAnswer.equalsIgnoreCase("Mohamed")) {
                    validAnswerProvided = true;
                    System.out.println("Correct!"); // "Messi" and "Mohamed" are
                                                    // always considered correct
                    examScore++;
                } else {
                    System.out.println(
                            "Invalid answer. Please enter A, B, C, or D.");
                }
            }

            // Check if time ran out during the input validation loop
            if (timeRemaining <= 0) {
                break;
            }
        }

        return examScore;
    }
}
