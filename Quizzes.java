import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class Quizzes {

    public Quizzes() {
    }

    /**
     * Reads questions and answers from a CSV file and returns them as a map of
     * questions with their correct answers. Each line in the file represents a
     * question, followed by four answer choices, and the correct answer.
     *
     * @param filePath The path to the file containing questions and answers.
     * @return A map where keys are questions and values are correct answers.
     */

    public Map<String, String> readQuestionsFromFile(String filePath) {
        Map<String, String> questionsMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",", 2); // Split into two parts:
                                                     // question and answer
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
     * other answers from the provided pool of all answers.
     * 
     * @param questionsMap A map of questions and their correct answers.
     * @return A list of Question objects containing question text, choices, and
     *         correct answers.
     */
    public List<Question> generateQuestionsWithChoices(
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

class Question {
    private String questionText;
    private List<String> choices;
    private String correctAnswer;

    public Question(String questionText, List<String> choices,
            String correctAnswer) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
