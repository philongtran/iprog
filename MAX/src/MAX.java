/**
 * This class is the starting point the game creation.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */
public class MAX {

	/**
	 * main method of the game
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Game game1 = new Game(8, 8);
		game1.run();
	}

}
