import java.util.InputMismatchException;
import java.util.Scanner;

public class MiamiQuest {
	
	public static void displayHelpMenu() {
		//Main menu before game begins
	    System.out.println("---- Help Menu ----");
	    System.out.println("Controls:");
	    System.out.println("- Type your answers and press Enter.");
	    System.out.println("- Type 'help' at any time to view this menu again.");
	    System.out.println("\nGame Rules:");
	    System.out.println("- Earn 10 credit points to graduate.");
	    System.out.println("- Passing a class earns you 2 credit points.");
	    System.out.println("- Pass classes by scoring at least 6 out of 10 on finals.");
	    System.out.println("- Bonuses and penalties affect your final exam score.");
	    System.out.println("\nTips:");
	    System.out.println("- Be quick on timed questions.");
	    System.out.println("- Pay attention to class attributes.");
	    System.out.println("- Have fun :)");
	    System.out.println("\nCommands:");
	    System.out.println("/drop : Drop the class, you only get 1 of these so choose wisely!");
	    System.out.println("/credits : Check the number of credits you currently have to check your progress.");
	    System.out.println("/restart : Restart the game and start back at freshman year, you may do this at any point throughout your game");
	    System.out.println("/clear : Clear the console! Feel free to do this if it becomes to cluttered with text.");
	    System.out.println("/retake : Retake an exam. You only get this option once throughout your game!");
	    System.out.println("--------------------");
	}
		
	public static void clearConsole() {
        // Print new lines to simulate clearing the console
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

	//start credits at 0 and keep track of them with the fail/pass exam game logic and print it using this method
	static int credits = 0; 
	 public static void displayCredits() {
	        System.out.println("You currently have " + credits + " credit points.");
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
						//Input start of game logic here.
					} else if (userChoice.equalsIgnoreCase("/credits")) { //check credits
						displayCredits();
					} else if (userChoice.equalsIgnoreCase("/drop")) { //drop a class
						//Input /drop game logic here
					} else if (userChoice.equalsIgnoreCase("/restart")) { //restart the game
						//Input /restart game logic here
					} else if (userChoice.equalsIgnoreCase("/clear")) { //clear console
						clearConsole();
					} else if (userChoice.equalsIgnoreCase("/retake")) { //retake an exam
						//Input /retake game logic here
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
