
/**
 * @Class: Quizzes
 * @Authors: Mohamed Lemine
 * @Written: 11/29/2024
 * @Course: CSE 201B: Intro to Software Engineering
 * @Purpose: The Quizzes class handles reading questions and answers from files,
 * generating multiple-choice options, and preparing the questions for the exams
 * in the MiamiQuest game.
 */

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class Quizzes {

    /**
     * Default constructor for the Quizzes class.
     */
    public Quizzes() {
        // Empty constructor
    }

    /**
     * Reads questions and answers from a CSV file and returns them as a map of
     * questions with their correct answers. Each line in the file represents a
     * question and its correct answer, separated by a comma.
     *
     * @param filePath The path to the file containing questions and answers.
     * @return A map where keys are questions and values are correct answers.
     */
    public Map<String, String> readQuestions(String filePath) {
        Map<String, String> questionsMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split the line into question and answer based on the first
                // comma
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String questionText = parts[0].trim();
                    String correctAnswer = parts[1].trim();
                    questionsMap.put(questionText, correctAnswer);
                } else {
                    System.out.println(
                            "Debug: Incomplete question data found, skipping question.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out
                    .println("Error reading questions file: " + e.getMessage());
        }
        return questionsMap;
    }

    /**
     * Generates multiple-choice options for each question by randomly selecting
     * other answers from the pool of all correct answers.
     *
     * @param questionsMap A map of questions and their correct answers.
     * @return A list of Question objects containing question text, choices, and
     *         correct answers.
     */
    public List<Question> questionsWithChoices(
            Map<String, String> questionsMap) {
        List<Question> questionsList = new ArrayList<>();
        Random random = new Random();
        List<String> allAnswers = new ArrayList<>(questionsMap.values());

        for (Map.Entry<String, String> entry : questionsMap.entrySet()) {
            String questionText = entry.getKey();
            String correctAnswer = entry.getValue();
            List<String> choices = new ArrayList<>();
            choices.add(correctAnswer);

            // Add random incorrect answers
            while (choices.size() < 4) {
                String randomAnswer = allAnswers
                        .get(random.nextInt(allAnswers.size()));
                if (!choices.contains(randomAnswer)) {
                    choices.add(randomAnswer);
                }
            }

            // Shuffle the choices to randomize their order
            Collections.shuffle(choices);

            questionsList
                    .add(new Question(questionText, choices, correctAnswer));
        }

        return questionsList;
    }

    /**
     * Displays a list of questions with their multiple choices.
     *
     * @param questionsWithChoices The list of Question objects to display.
     */
    public void displayQuestionsWithChoices(
            List<Question> questionsWithChoices) {
        if (questionsWithChoices == null || questionsWithChoices.isEmpty()) {
            System.out.println("No questions available to display.");
        } else {
            System.out.println("Questions with Choices:");
            for (int i = 0; i < questionsWithChoices.size(); i++) {
                Question question = questionsWithChoices.get(i);
                System.out.println(
                        "\n" + (i + 1) + ". " + question.getQuestionText());
                List<String> choices = question.getChoices();
                for (int j = 0; j < choices.size(); j++) {
                    System.out
                            .println((char) ('A' + j) + ") " + choices.get(j));
                }
            }
        }
    }
}

/**
 * @Class: Question
 * @Purpose: Represents a single question with multiple-choice answers.
 */
class Question {
    private String questionText; // The text of the question
    private List<String> choices; // The list of multiple-choice answers
    private String correctAnswer; // The correct answer to the question

    /**
     * Constructor for the Question class.
     *
     * @param questionText  The text of the question.
     * @param choices       The list of multiple-choice answers.
     * @param correctAnswer The correct answer to the question.
     */
    public Question(String questionText, List<String> choices,
            String correctAnswer) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets the text of the question.
     *
     * @return The question text.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Gets the list of multiple-choice answers.
     *
     * @return The list of choices.
     */
    public List<String> getChoices() {
        return choices;
    }

    /**
     * Gets the correct answer to the question.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
