
/**
 * 
 * @author John.Murphy
 * @version 1.0
 */
public class Game {
	Instructions instruct = new Instructions();
	User user = new User();
	
	// Variables
	private boolean gameOver;// = false;
	private boolean onMarketSide;// = true;
	private boolean boatStillAtSide;
	private int userChoice;// = 0;
	
	// Sides
	private Enum marketSide[] = new Enum[3];// {Passengers.DOG, Passengers.CHICKEN, Passengers.CORN};
	private Enum homeSide[] = new Enum[3];
	private Enum<Passengers> boatContains;// = null;	
	
	/**
	 * Method that starts the game.
	 */
	public void startGame() {
		
		resetGame();
		
		while (!isGameOver(marketSide, homeSide)) {
			
			if (homeSideIsFull()) {
				gameOver = true;
				System.out.println("You Win!");
				break;
			}
			
			if (onMarketSide) {
				instruct.whatSide("market");
				
				System.out.print("\nThe market side has: ");
				for (int i = 0; i < marketSide.length; i++) {
					if (marketSide[i] != null) {
						System.out.print(marketSide[i].toString().toLowerCase() + " ");
					}
				}
					
				while (boatStillAtSide) {
					instruct.askUser();
					instruct.listOptions(marketSide, boatContains);
					userChoice = user.choice();
					checkUserChoice(marketSide, homeSide);
				}
				onMarketSide = false;
				boatStillAtSide = true;
				
				
			} else { 
				
				instruct.whatSide("home");
				
				// Prints what's at the home side.
				System.out.print("\nThe home side has: ");
				for (int i = 0; i < homeSide.length; i++) {
					if (homeSide[i] != null) {
						System.out.print(homeSide[i].toString().toLowerCase() + " ");
					}
				}
				while (boatStillAtSide) {
					instruct.askUser();
					instruct.listOptions(homeSide, boatContains);
					userChoice = user.choice();
					checkUserChoice(homeSide, marketSide);
				}
				onMarketSide = true;
				boatStillAtSide = true;
			}
		}
	}
	
	/**
	 * Checks if the home side has all three passengers.
	 * @return True if full.
	 */
	private boolean homeSideIsFull() {
		int length = homeSide.length;
		int count = 0;
		boolean isFull = false;
		
		for (int i = 0; i < length; i++) {
			if (homeSide[i] != null) count++;
			if (count == 3) isFull = true;
		}
		
		return isFull;
	}
	
	 /**
	  * Put the specified passenger in the boat
	  * @param passenger The passenger to put in the boat
	 * @param currentSide The side from which to remove the passenger.
	  * 			Usually the side we are currently on.
	  */
	private void putInBoat(Passengers passenger, Enum[] currentSide) {
		boatContains = passenger;
		
		// Loop through market side to find and remove the passenger.
		for (int i = 0; i < currentSide.length; i++) {
			if (currentSide[i] == passenger) {
				currentSide[i] = null;
			}
		}
		
		System.out.println("You take the " + passenger.toString().toLowerCase() + ".");
		
		boatStillAtSide = true;
	}
	
	/**
	 * Checks if a passenger is on a certain side.
	 * @param passenger The passenger to check for.
	 * @param side The side to check for a certain passenger.
	 * @return True if the passenger is on the side specified.
	 */
	private boolean alreadyMoved(Passengers passenger, Enum[] side) {
		boolean moved = false;
		for (int i = 0; i < side.length; i++) {
			if (side[i] == passenger) {
				System.out.format("You already moved the %s", passenger.toString().toLowerCase());
				moved = true;
			}
		}
		
		return moved;
	}
	
	/**
	 * Puts the passenger that is currently in the boat on the the side we are on.
	 * @param side The side to put down the passenger on.
	 * @param passenger The passenger in the boat.
	 */
	private void setDownPassenger(Enum[] side, Enum<Passengers> passenger) {
		for (int i = 0; i < side.length; i++) {
			if (side[i] == null) {
				side[i] = passenger;
				break;
			}
		}
		System.out.println("You set down the " + passenger.toString().toLowerCase() + ".");
		boatContains = null;
		boatStillAtSide = true;
	}
	
	/**
	 * Checks the users choice and performs the action they have selected.
	 * @param currentSide The side we are currently on.
	 * @param sideTo The side we want to move passengers to.
	 */
	private void checkUserChoice(Enum[] currentSide, Enum[] sideTo) {
		if (userChoice > instruct.checkOptions(instruct.getOptionArray())) {
			System.out.println("Pick another choice");
			return;
		}
		for (int i = 0; i < instruct.getOptionArray().length; i++) {
			if (instruct.getOptionArray()[i][0] != 0) {
				if (instruct.getOptionArray()[i][0] == userChoice &&
						instruct.getOptionArray()[i][1] == Constants.DOG) {
					// Choice is dog
					if (alreadyMoved(Passengers.DOG, sideTo)) break;
					
					if (boatContains == null) {
						putInBoat(Passengers.DOG, currentSide);
					} else System.out.println("There is already something in the boat.");
					
					break;
					
				} else if (instruct.getOptionArray()[i][0] == userChoice &&
						instruct.getOptionArray()[i][1] == Constants.CHICKEN) {
					// Choice is chicken
					if (alreadyMoved(Passengers.CHICKEN, sideTo)) break;
					
					if (boatContains == null) {
						putInBoat(Passengers.CHICKEN, currentSide);
					} else System.out.println("There is already something in the boat.");
					break;
					
				} else if (instruct.getOptionArray()[i][0] == userChoice &&
						instruct.getOptionArray()[i][1] == Constants.CORN) {
					// Choice is corn
					if (alreadyMoved(Passengers.CORN, sideTo));
					
					if (boatContains == null) {
						putInBoat(Passengers.CORN, currentSide);
					} else System.out.println("There is already something in the boat.");
					break;
				} else if (instruct.getOptionArray()[i][0] == userChoice &&
						instruct.getOptionArray()[i][1] == Constants.CHANGE_SIDES) {
					// Choice is to change sides
					boatStillAtSide = false;
					System.out.println("You change sides.");
					// 
				} else if (instruct.getOptionArray()[i][0] == userChoice &&
						instruct.getOptionArray()[i][1] == Constants.SET_DOWN) {
					// Choice is to set down
					setDownPassenger(currentSide, boatContains);
				} else if (instruct.getOptionArray()[i][0] == userChoice &&
						instruct.getOptionArray()[i][1] == Constants.QUIT) {
					// Choice is to quit
					// TODO add functionality to quit.
					System.out.println("You quit.");
					System.exit(0);
				}
			}
		}
	}
	
	private boolean isGameOver(Enum[] marketSide, Enum[] homeSide) {
		//boolean gameOver = false;
		
		for (int i = 0; i < marketSide.length; i++) {
			if (countNumPassengers(marketSide) == 2) {
				if (sideContains(marketSide, Passengers.DOG) &&
						sideContains(marketSide, Passengers.CHICKEN)) {
					gameOver = true;
					printYouLose("dogAteChicken");
					break;
				} else if (sideContains(marketSide, Passengers.CHICKEN) &&
					sideContains(marketSide, Passengers.CORN)) {
					gameOver = true;
					printYouLose("chickenAteCorn");
					break;
				}
			}
		}
		
		for (int i = 0; i < homeSide.length; i++) {
			if (countNumPassengers(homeSide) == 2) {
				if (sideContains(homeSide, Passengers.DOG) &&
						sideContains(homeSide, Passengers.CHICKEN)) {
					gameOver = true;
					printYouLose("dogAteChicken");
					break;
				} else if (sideContains(homeSide, Passengers.CHICKEN) && 
						sideContains(homeSide, Passengers.CORN)) {
					gameOver = true;
					printYouLose("chickenAteCorn");
					break;
				}
			}
		}
		
		return gameOver;
	}
	
	private boolean sideContains(Enum[] side, Enum passenger) {
		boolean contains = false;
		for (int i = 0; i < side.length; i++) {
			if (side[i] == passenger) contains = true;
		}
		
		return contains;
	}
	
	private int countNumPassengers(Enum[] side) {
		int count = 0;
		for (int i = 0; i < side.length; i++) {
			if (side[i] != null) count++;
		}
		
		return count;
	}
	
	/**
	 * Prints a message letting the player know they have lost and also the 
	 * reason why they lost.
	 * @param loseCondition This determines how the user lost the game.
	 */
	private void printYouLose(String loseCondition) {
		if (loseCondition.toLowerCase().equals("dogatechicken"))
			System.out.println("The dog ate the chicken");
		else if (loseCondition.toLowerCase().equals("chickenatecorn"))
			System.out.println("The chicken ate the corn.");
		
		System.out.println("You lose");
		System.out.println("Do you want to play again? Y | N ?");
	}
	
	private void resetGame() {
		gameOver = false;
		onMarketSide = true;
		boatStillAtSide = true;
		userChoice = 0;
		
		marketSide[0] = Passengers.DOG;
		marketSide[1] = Passengers.CHICKEN;
		marketSide[2] = Passengers.CORN;
		
		for (int i = 0; i < homeSide.length; i++) {
			homeSide[i] = null;
		}
		
		boatContains = null;
	}
}
