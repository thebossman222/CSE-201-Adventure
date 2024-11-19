/**
 * @Class: Class
 * @authors: Caleb Krainman, Corbin Fulton, Andy Roberts, Mohamed Lemine E, Marissa Ellis, Ethan Jones
 * @version 1.0
 * @Written: 11/1/2024
 * @Course: CSE 201B: Intro to Software Engineering
 * @Purpose: The Class class represents a course in the MiamiQuest game, 
 * encompassing its scheduled time, associated professor, and difficulty level.
 * This class allows for the creation of a class with randomized 
 * attributes, including its time and the difficulty assigned by 
 * a randomly generated professor. It also provides methods to 
 * display class information and assign a professor to the class.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Class {
    private String time;
    private String professorDifficulty;
    private Professor professor; // Association with Professor
    private List<Question> questions; // List to store questions

    /**
     * Constructor to initialize the Class with a new Professor instance.
     * Randomize the difficulty level of the professor (easy or hard) and the class time (8 AM or normal).
     */
    public Class() {
        this.professor = new Professor(); // Assigns a new Professor
        this.professorDifficulty = professor.randomizeDifficulty(); // Sets the professor's difficulty
        this.time = randomizeClassTime(); // Sets the class time
        this.questions = new ArrayList<>(); // Initialize the questions list
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

    /**
     * Getter for the professor difficulty.
     * 
     * @return The difficulty of the professor.
     */
    public String getProfessorDifficulty() {
        return this.professorDifficulty;
    }

    /**
     * Getter for the questions list.
     * 
     * @return The list of questions for the class.
     */
    public List<Question> getQuestions() {
        return this.questions;
    }
}
