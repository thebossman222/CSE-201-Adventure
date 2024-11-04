/**
 * @Class: MiamiQuest
 * @authors: Caleb Krainman, Corbin Fulton, Andy Roberts, Mohamed Lemine E, Marissa Ellis, Ethan Jones
 * @version 1.0
 * @Written: 11/1/2024
 * @Course: CSE 201B: Intro to Software Engineering
 * @Purpose: The MiamiQuest class serves as the main entry point for the 
 * MiamiQuest game. It manages the game flow, including starting classes, 
 * handling user inputs, and tracking player credits. Players navigate through 
 * different classes, aiming to earn enough credits to graduate.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class MiamiQuest {

    static boolean canDrop = true;
    static boolean canRetake = true;
    
    /** 
     * Displays the help menu with controls, game rules, tips, and commands.
     */
    public static void displayHelpMenu() {
        System.out.println("---- Help Menu ----\n"
                + "Controls:\n"
                + "- Type your answers and press Enter.\n"
                + "- Type 'help' at any time to view this menu again.\n"
                + "Game Rules:\n"
                + "- Earn 10 credit points to graduate.\n"
                + "- Passing a class earns you 2 credit points.\n"
                + "- Pass classes by scoring at least 6 out of 10 on finals.\n"
                + "- Bonuses and penalties affect your final exam score.\n"
                + "Tips:\n"
                + "- Be quick on timed questions.\n"
                + "- Pay attention to class attributes.\n"
                + "- Have fun :)\n"
                + "Commands:\n"
                + "/drop : Drop the class, you only get 1 of these so choose wisely!\n"
                + "/credits : Check the number of credits you currently have to check your progress.\n"
                + "/restart : Restart the game and start back at freshman year, you may do this at any point throughout your game\n"
                + "/clear : Clear the console! Feel free to do this if it becomes too cluttered with text.\n"
                + "/retake : Retake an exam. You only get this option once throughout your game!\n"
                + "--------------------\n");
    }
        
    /**
     * Clears the console by printing empty lines.
     */
    public static void clearConsole() {
        System.out.println("Clearing console...");
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    // Track credits earned
    static int credits = 0; 

    /**
     * Displays the current credit points the player has earned.
     */
    public static void displayCredits() {
        System.out.println("You currently have " + credits + " credit points.");
    }

    /**
     * Starts the game by creating a new Class instance
     * and displaying its characteristics.
     */
    public static void start() {
        Class currentClass = new Class(); // Create a new Class instance
        System.out.println("Class started with the following characteristics:");
        System.out.println(currentClass.displayClassInfo());
    }

    /**
     * Allows the player to retake the exam if they haven't already done so.
     * The new score is randomly generated and credits are updated based on the result.
     */
    public static void retakeExam() {
        if (canRetake) {
            canRetake = false; // Mark that retake has been used
            System.out.println("Retaking the exam...");
            
            int newScore = (int) (Math.random() * 10) + 1;
            System.out.println("Your new exam score is: " + newScore);

            // Check if new score meets the passing threshold
            if (newScore >= 6) {
                credits += 2; // Add credits if passed
                System.out.println("Congratulations! You passed the exam and earned 2 credits.");
            } else {
                System.out.println("You did not pass the exam. Better luck next time.");
            }
        } else {
            System.out.println("You have already used your retake option.");
        }
    }

    /**
     * Restarts the game, resetting credits, drop option, and retake option.
     */
    public static void restartGame() {
        System.out.println("Restarting the game...");
        credits = 0; // Reset credits
        canDrop = true; // Reset drop option
        canRetake = true; // Reset retake option
        start(); // Start a new class
    }

    /**
     * Allows the player to drop the current class if they haven't already done so.
     */
    public static void dropClass() {
        if (canDrop) {
            canDrop = false; // Mark that drop has been used
            System.out.println("Dropping the class...");
            start(); // Start a new class immediately
        } else {
            System.out.println("You have already used your drop option.");
        }
    }

    /**
     * Main method to run the MiamiQuest game.
     * It handles user inputs and manages the game flows
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // A loop that runs until the user prompts it to stop.
        while (true) {
            try {
                System.out.println("'help': Display Help menu");
                System.out.println("'start': Start Game");
                System.out.println("'stop': Exit the program.");
                
                String userChoice = scanner.nextLine();

                // Handle user input commands
                if (userChoice.equalsIgnoreCase("help")) {
                    displayHelpMenu();
                } else if (userChoice.equalsIgnoreCase("stop")) {
                    System.out.println("Stopping program...");
                    scanner.close();
                    return; // Stop the loop.
                } else if (userChoice.equalsIgnoreCase("start")) {
                    start(); // Start the game
                } else if (userChoice.equalsIgnoreCase("/credits")) {
                    displayCredits(); // Check credits
                } else if (userChoice.equalsIgnoreCase("/drop")) {
                    dropClass(); // Drop a class
                } else if (userChoice.equalsIgnoreCase("/restart")) {
                    restartGame(); // Restart the game
                } else if (userChoice.equalsIgnoreCase("/clear")) {
                    clearConsole(); // Clear console
                } else if (userChoice.equalsIgnoreCase("/retake")) {
                    retakeExam(); // Retake an exam
                } else {
                    System.out.println("Invalid choice. Please try again with a different input.");
                }
                
            } catch (InputMismatchException e) {
                // Handle invalid input gracefully
                System.out.println("Invalid Input! Please enter only a valid command.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
