import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Deals with user interaction.
 * @author John.Murphy
 * @version 1.0
 */
public class User {
	private Scanner sc = new Scanner(System.in);
	
	private int userChoice;
	
	/**
	 * Allows the user to enter their choice into the console.
	 * @return The users choice as an integer.
	 */
	public int choice() {
		try {
			userChoice = Integer.parseInt(sc.nextLine());
			
		} catch (InputMismatchException | NumberFormatException e) {
			System.out.println("Please enter a valid choice.");
			choice();
		}
		return userChoice;
	}
	
	/**
	 * Used when asking the user if they would like to play or if they have finished, 
	 * asking if they would like to play again. This method only allows either a 'y' or
	 * a 'n' to be entered, all other inputs are rejected.
	 * @return The users choice whether to play again.
	 */
	public String play() {
		String choice;
		choice = sc.nextLine().toLowerCase();
		
		while (!choice.equals("y") && !choice.equals("n")) {
			System.out.println("Please enter either 'Y' for yes or 'N' for no.");
			choice = sc.nextLine();
		}
		
		return choice;
	}
}
