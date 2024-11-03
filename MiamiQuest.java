import java.util.InputMismatchException;
import java.util.Scanner;

public class MiamiQuest {
	
    static boolean canDrop = true;
    static boolean canRetake = true;
	
	public static void displayHelpMenu() {
		//Main menu before game begins
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
				+ "/clear : Clear the console! Feel free to do this if it becomes to cluttered with text.\n"
				+ "/retake : Retake an exam. You only get this option once throughout your game!\n"
				+ "--------------------\n");
	}
		
	public static void clearConsole() {
		System.out.println("Clearing console...");
		for (int i = 0; i < 50; i++)
			System.out.println();
	}

	//start credits at 0 and keep track of them with the fail/pass exam game logic and print it using this method
	static int credits = 0; 
	public static void displayCredits() {
		System.out.println("You currently have " + credits + " credit points.");
	}


	public static void start() {
		// Create a new Class instance, which will automatically assign a Professor and randomize attributes
		Class currentClass = new Class();

		// Display the class information
		System.out.println("Class started with the following characteristics:");
		System.out.println(currentClass.displayClassInfo());
	}
	
	public static void retakeExam() {
		if(canRetake) {
			canRetake = false;
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
	
	public static void restartGame() {
        System.out.println("Restarting the game...");
        credits = 0; // Reset credits
        canDrop = true; // Reset drop option
        canRetake = true; // Reset retake option
        start(); // Start a new class
    }
	
	public static void dropClass() {
        if (canDrop) {
            canDrop = false; // Mark that drop has been used
            System.out.println("Dropping the class...");

            start(); // Start a new class immediately or set up the game for the next class
        } else {
            System.out.println("You have already used your drop option.");
        }
    }

	
	public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			
			//A loop that runs until the user prompts it to stop.
			while (true) {
				try {
					System.out.println("'help': Display Help menu");
					System.out.println("'start'. Start Game");
					System.out.println("'stop'. Exit the program.");
					
					String userChoice = scanner.nextLine();

					//This is the current implementation of the user input menu, feel free to add
					//other choices by using else if statements and leaving the final else statement
					//there. 

					
					if (userChoice.equalsIgnoreCase("help")) { //Display help menu
						displayHelpMenu();
						
					} else if (userChoice.equalsIgnoreCase("stop")) { // Exit program
						System.out.println("Stopping program...");
						scanner.close();
						return; //Stop the loop.
						
					} else if (userChoice.equalsIgnoreCase("start")) { //start the game by randomizing professor difficulty/class time features
						start();
					} else if (userChoice.equalsIgnoreCase("/credits")) { //check credits
						displayCredits();
					} else if (userChoice.equalsIgnoreCase("/drop")) { //drop a class
						dropClass();
					} else if (userChoice.equalsIgnoreCase("/restart")) { //restart the game
						restartGame();
					} else if (userChoice.equalsIgnoreCase("/clear")) { //clear console
						clearConsole();
					} else if (userChoice.equalsIgnoreCase("/retake")) { //retake an exam
						retakeExam();
					} else {
						System.out.println("Invalid choice. Please try again with a different input");

					}
					
				} catch (InputMismatchException e) { //This stops the loop from breaking with a typo
					System.out.println("Invalid Input! Please enter only a number!");
					String userChoice = scanner.nextLine(); 
			}
		}
	}
}
