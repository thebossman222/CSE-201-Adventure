
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
				System.out.println("1. Display Help menu");
				//Put other things we can do here

				System.out.println("0. Exit the program.");
				
				int userChoice = scanner.nextInt();
	
				if (userChoice == 1) { //Display help menu
					displayHelpMenu();
					
				} else if (userChoice == 0) { // Exit program
					System.out.println("Stopping program...");
					scanner.close();
					return; //Stop the loop.
				} else {
					System.out.println("Invalid choice. Please try again with a different input");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input! Please enter only a number!");
	}




}
