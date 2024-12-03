/**
 * @Class: MiamiQuest
 * @authors: Caleb Krainman, Corbin Fulton, Andy Roberts, Mohamed Lemine E, Marissa Ellis, Ethan Jones
 * @Written: 11/22/2024
 * @Course: CSE 201B: Intro to Software Engineering
 * @Purpose: The MiamiQuest class serves as the main entry point for the 
 * MiamiQuest game. It manages the game flow, including starting classes, 
 * handling user inputs, and tracking player credits. Players navigate through 
 * different classes, aiming to earn enough credits to graduate.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class MiamiQuest {

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
                + "/classes : Display all registered classes.\n"
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

    /**
     * Displays the current credit points the player has earned.
     */
    public static void displayCredits(Player player) {
        player.checkCredits(); // Check credits through player instance
    }

    /**
     * Starts the game by creating a new Class instance and displaying its
     * characteristics.
     */
    public static void start(Player player) {
        Scanner sc = new Scanner(System.in);
        Course currentClass = player.registerCourse(); // Register player for a
                                                       // new class
        System.out.println("Course started with the following characteristics: \n");
        System.out.println(currentClass.displayCourseInfo() + "\n");

        System.out.print("Enter S to take the Exam: ");
        while (true) {
            String in = sc.nextLine();
            if (in.equalsIgnoreCase("s")) {
                player.takeExam();
            } else
                System.out.println("Try again");
            break;
        }

    }

    /**
     * Allows the player to retake the exam if they haven't already done so.
     */
    public static void retakeExam(Player player) {
        if (player.retakeExam()) { // retakeExam() in Player returns true if
                                   // successful
            System.out.println("Retaking the exam...");
        } else {
            System.out.println("You have already used your retake option.");
        }
    }

    /**
     * Main method that runs the MiamiQuest game. It handles user inputs and
     * manages the game flow by running a loop that will not stop until the user prompts
     * the game to stop or wins. Uses a scanner to read inputs and throws InputMismatchException
     * if user inputs a typo.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(); // Create a new Player instance at the
                                      // start
        GameController gameController = new GameController(); // Create a
                                                              // GameController
                                                              // instance

        // Start the game using GameController
        gameController.startGame();

        // A loop that runs until the user prompts it to stop.
        // Each eser command is stored in userChoice until game is finished.
        // A list of available commands are accessible with 'help'
        while (true) {
            try {
                System.out.println("'help': Display Help & Commands menu");
                System.out.println("'start': Start Game");
                System.out.println("'stop': Exit the program.");
                
                String userChoice = scanner.nextLine();

                // Handle user input commands
                if (userChoice.equalsIgnoreCase("help")) {
                    displayHelpMenu();
                } else if (userChoice.equalsIgnoreCase("stop")) {
                    gameController.endGame(); // End the game using
                                              // GameController
                    scanner.close();
                    return; // Stop the loop.
                } else if (userChoice.equalsIgnoreCase("start")) {
                    start(player); // Start a new class for player
                } else if (userChoice.equalsIgnoreCase("/credits")) {
                    displayCredits(player); // Check player credits
                } else if (userChoice.equalsIgnoreCase("/classes")) {
                    player.displayClasses(); // Display all registered classes
                } else if (userChoice.equalsIgnoreCase("/drop")) {
                    player.dropCourse(); // Drop a class through Player instance
                    start(player); // Start a new class for player
                } else if (userChoice.equalsIgnoreCase("/restart")) {
                    gameController.restartGame(); // Restart the game using
                                                  // GameController
                    player = new Player(); // Reset the player to a new instance
                    start(player); // Start a new class for player
                } else if (userChoice.equalsIgnoreCase("/clear")) {
                    clearConsole(); // Clear console for user
                } else if (userChoice.equalsIgnoreCase("/retake")) {
                    retakeExam(player); // Retake an exam through Player
                                        // instance
                } else if (userChoice.equalsIgnoreCase("/pause")) {
                    gameController.pauseGame(); // Pause the game using
                                                // GameController
                } else {
                    System.out.println(
                            "Invalid choice. Please try again with a different input."); // Invalid input handling
                }

            } catch (InputMismatchException e) {
                // Handle invalid input gracefully
                System.out.println(
                        "Invalid Input! Please enter only a valid command.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
