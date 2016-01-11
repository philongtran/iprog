/**
 * This class is the starting point of the game creation.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class MAX {

	/**
	 * Main method of the game.
	 * 
	 * @param args
	 *            - Arguments from the command line
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Game game = new Game(8, 8, 2);
		game.run();
	}

}
