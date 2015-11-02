/**
 * Gives instructions and information to the user.
 * @author John.Murphy
 *
 */
public class Instructions {
	
	private int[][] optionArray = new int[6][2];
	
	/**
	 * Prints instructions for the user to follow.
	 */
	public void printInstructions() {
		System.out.println("Welcome to the River Challenge. \n");
		System.out.println("You are a farmer coming back from the market. \n" + 
				"You have just bought a chicken and a sack of corn. \n" + 
				"You also brought your dog along for company. \n");

		System.out.println("As you return home you arrive at the river. \n" +
				"You used your boat to cross the river as you were on your way to the market \n" +
				"but now you need to plan a way to get back over. \n" + 
				"You need to get all three things across the river but you can only take \n" +
				"one thing at a time. \n" + 
				"If you leave the dog and chicken alone, the dog will eat the chicken and if \n" +
				"you leave the chicken with the corn the chicken will eat the corn.\n"
				+ "Press '9' to quit at any time. \n"
				+ "Do you want to play Y | N ?");
	}
	
	/**
	 * Prints out a list of options for the user to choose from.
	 */
	public void listOptions(Enum[] passengers, Enum<Passengers> boatContains) {
		int length = passengers.length;
		int optionCounter = 1;
		resetOptionArray(optionArray);
		initialiseIdentifiers(optionArray);
		
		for (int i = 0; i < length; i++) {
			if(passengers[i] != null) {
				//optionArray[i] = optionCounter;
				System.out.println(optionCounter + ". Take the " + passengers[i].toString().toLowerCase());
				if (passengers[i] == Passengers.DOG) optionArray[0][0] = optionCounter;
				else if (passengers[i] == Passengers.CHICKEN) optionArray[1][0] = optionCounter;
				else if (passengers[i] == Passengers.CORN) optionArray[2][0] = optionCounter;
				optionCounter++;
			}
		}
		
		System.out.println(optionCounter + ". Change sides");
		optionArray[3][0] = optionCounter;
		
		if (boatContains != null) {
			
			System.out.println(optionCounter + 1 + ". Set down " + boatContains.toString().toLowerCase());
			optionArray[4][0] = optionCounter + 1;
		}
		
		System.out.println("9. Quit");
		optionArray[5][0] = 9;
		
		/*System.out.println("0. Quit");
		optionArray[5][0] = 15;*/
	}
	
	/**
	 * Prints a line that asks the user to pick a choice.
	 */
	public void askUser() {
		System.out.println("\nWhat will you do?");
	}
	
	/**
	 * Prints out what side the user is on based on the side variable.
	 * @param side A string that will be printed along with a statement
	 * identifying the side the user is on.
	 */
	public void whatSide(String side) {
		System.out.format("\nYou are on the %s side.", side);
	}
	
	/**
	 * Adds numbers to the optionArray that act as identifiers for the various
	 * options available to the user. Eg. 1 is used as an identifier for the dog
	 * so that when the users wants to move the dog the program knows that the
	 * Enum DOG is associated with the number 1.
	 * @param options The optionArray
	 */
	private void initialiseIdentifiers(int[][] options) {
		for (int i = 0; i < options.length; i++ ) {
			options[i][1] = i;
		}
	}
	
	/**
	 * Resets the optionArray back to empty so it can be used again.
	 * @param options The array to reset
	 */
	private void resetOptionArray(int[][] options) {
		for (int i = 0; i < options.length; i++) {
			for (int j = 0; j < options[1].length; j++) {
				options[i][j] = 0;
			}
		}
	}
	
	/**
	 * 
	 * @return optionArray
	 */
	public int[][] getOptionArray() {
		return optionArray;
	}
	
	/**
	 * Method that checks the amount of options available to the user and limits
	 * them to those options.
	 * @param options The optionArray.
	 */
	public int checkOptions(int[][] options) {
		int max = 0;
		for (int i = 0; i < options.length - 1; i++) {
			if (options[i+1][0] > options [i][0]) {
				max = options[i+1][0];
			}
		}
		return max;
	}
	
}
