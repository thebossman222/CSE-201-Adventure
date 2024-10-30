
public class MiamiQuest {

	public void displayHelpMenu() {
    System.out.println("---- Help Menu ----");
    System.out.println("Controls:");
    System.out.println("- Type your answers and press Enter.");
    System.out.println("- Type 'help' at any time to view this menu again.");
    System.out.println("\nGame Rules:");
    System.out.println("- Earn 10 credit points to graduate.");
    System.out.println("- Pass classes by scoring at least 6 out of 10 on finals.");
    System.out.println("- Bonuses and penalties affect your final exam score.");
    System.out.println("\nTips:");
    System.out.println("- Be quick on timed questions.");
    System.out.println("- Pay attention to class attributes.");
    System.out.println("--------------------");
}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//A loop that runs until the user prompts it to stop.
		while (true) {
			try {
				System.out.println("'help': Display Help menu");
				//Put other things we can do here
				System.out.println("'start'. Start Game");
				System.out.println("'stop'. Exit the program.");
				
				string userChoice = scanner.nextLine();

				//This is the current implementation of the user input menu, feel free to add
				//other choices by using else if statements and leaving the final else statement
				//there. 

				
				if (userChoice.equalsIgnoreCase("help")) { //Display help menu
					displayHelpMenu();
				} else if (userChoice.equalsIgnoreCase("stop") { // Exit program
					System.out.println("Stopping program...");
					scanner.close();
					return; //Stop the loop.
				} else if (userChoice.equalsIgnoreCase("start")) { 
					//Input start of game logic here.
				} else if (userChoice.equalsIgnoreCase("credits") {
					//Other user option here.
				} else {
					System.out.println("Invalid choice. Please try again with a different input");

				}
				
			} catch (InputMismatchException e) { //This stops the loop from breaking with a typo
				System.out.println("Invalid Input! Please enter only a number!");
				int userChoice = scanner.nextLine(); 
	}




}
