/**
 * Contains main method.
 * @author John.Murphy
 * @version 1.0
 */
public class RiverChallenge {

	public static void main(String[] args) {
		Game game = new Game();
		Instructions instruct = new Instructions();
		User user = new User();
		
		instruct.printInstructions();
		
		
		/*String temp = user.play();
		while (!temp.equals("y") && !temp.equals("n")) {
			System.out.println("Please enter either 'Y' for yes or 'N' for no.");
			temp = user.play();
		}*/
		
		while (user.play().equals("y")) {
			game.startGame();
		}
	}

}
