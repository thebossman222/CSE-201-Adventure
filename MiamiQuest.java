
/**
 * @Class: MiamiQuest
 * @Authors: Caleb Krainman, Corbin Fulton, Andy Roberts, Mohamed Lemine E, Marissa Ellis, Ethan Jones
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
                + "Game Controls:\n"
                + "- Navigate the game using the menu options provided.\n"
                + "- Enter the number corresponding to your choice.\n"
                + "Game Rules:\n"
                + "- Earn 12 credit points and pass at least 4 courses to graduate.\n"
                + "- Passing a class earns you 3 credit points.\n"
                + "- Pass classes by scoring at least 6 out of 10 on exams.\n"
                + "- You have one opportunity to retake an exam and one to drop a course.\n"
                + "Tips:\n"
                + "- Focus on passing your courses to accumulate credits.\n"
                + "- Use your retake and drop options wisely.\n"
                + "- Have fun and good luck!\n"
                + "--------------------\n");
    }

    /**
     * Clears the console by printing empty lines.
     */
    public static void clearConsole() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    /**
     * Displays the current credit points the player has earned.
     */
    public static void displayCredits(Player player) {
        player.getCredits(); // Display credits through player instance
    }

    /**
     * Starts the game by creating a new Course instance and initiating the
     * exam.
     */
    public static void start(Player player) {
        Scanner sc = new Scanner(System.in);
        Course currentCourse = player.registerCourse();
        // Register player for a new course
        currentCourse.displayCourseInfo();
        
        System.out.println(
                "Course started with the following characteristics: \n");

        System.out.println(currentCourse.displayCourseInfo() + "\n");
        // Automatically start the exam
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
        if (player.retakeExam()) { // Pass scanner to player's method
            System.out.println("Retaking the exam...");
        } else {
            System.out.println(
                    "You have already used your retake option or have no failed courses.");
        }
    }

    /**
     * Main method that runs the MiamiQuest game. It handles user inputs and
     * manages the game flow by running a loop until the player graduates,
     * fails, or exits.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(); // Create a new Player instance at the
                                      // start
        GameController gameController = new GameController(); // Create a
                                                              // GameController
                                                              // instance

        System.out.println("Enter 'start' to Start The Game \n");
        String in = scanner.nextLine();

        if (in.equalsIgnoreCase("start")) {

            // Start the game using GameController
            gameController.startGame();

            // Automatically start the first course and exam
            start(player);

            // Main game loop
            while (!player.isGameWon()) {

                try {
                    // Check for graduation or failure conditions
                    if (player.canGraduate()) {
                        System.out.println(
                                "You've graduated! Congratulations on winning the game.");
                        gameController.endGame();
                        scanner.close();
                        return;
                    }

                    if (player.getFailedExams() > 2) {
                        player.setGameWon(true);
                        System.out.println(
                                "You've failed multiple classes and flunked out. You lose.");
                        gameController.endGame();
                        scanner.close();
                        return;
                    }

                    // Display menu options
                    System.out.println("\nMenu Options:");
                    System.out.println("1. Register and start next course");
                    System.out.println("2. Check credits");
                    System.out.println("3. Display registered courses");
                    System.out.println("4. Use retake exam option");
                    System.out.println("5. Use drop course option");
                    System.out.println("6. Display help menu");
                    System.out.println("7. Clear console");
                    System.out.println("8. Exit game");

                    System.out.print("Enter your choice: ");
                    int userChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (userChoice) {
                    case 1:
                        start(player); // Register and start next course
                        break;
                    case 2:
                        displayCredits(player); // Check player credits
                        break;
                    case 3:
                        player.displayCourses(); // Display all registered
                                                 // courses
                        break;
                    case 4:
                        retakeExam(player); // Pass scanner
                        break;
                    case 5:
                        player.dropCourse(); // Pass scanner
                        break;
                    case 6:
                        displayHelpMenu(); // Display help menu
                        break;
                    case 7:
                        clearConsole(); // Clear console
                        break;
                    case 8:
                        gameController.endGame(); // End the game
                        scanner.close();
                        return;
                    default:
                        System.out.println(
                                "Invalid choice. Please enter a number from 1 to 8.");
                        break;
                    }

                } catch (InputMismatchException e) {
                    // Handle invalid input gracefully
                    System.out.println(
                            "Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
        }
    }
}
