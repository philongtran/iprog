/**
 * This class represents the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */
public class MAX {

	private static Player player1; // player 1
	private static Player player2; // player 2
	private static Board board; // board

	/**
	 * main method of the game
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int oldPositionX; // temporary holder of the old x position of the
							// player
		int oldPositionY; // temporary holder of the old y position of the
							// player

		// set starting position of the players and who's active
		player1 = new Player(4, 4, -1);
		player1.setActive(true);
		player2 = new Player(3, 3, -2);
		player2.setActive(false);
		// initialize the board and its size
		board = new Board(8, 8);
		// plants the players on the board
		board.setPlayer(player1.getX(), player1.getY(), player1.getColor());
		board.setPlayer(player2.getX(), player2.getY(), player2.getColor());
		// prints the score on the screen and which players turn it is
		System.out.print("Score B: " + player1.getScore() + " Score W: " + player2.getScore() + " | ");
		System.out.println("Black to move");
		// prints the board on the screen
		board.show();

		// runs while players are below score
		while (player1.getScore() < 100 && player2.getScore() < 100) {
			// reads keyboard input to move the active player
			String input = IO.promptAndRead("i: ");
			// cases which are allowed
			switch (input) {
			// moves the player up
			case "w":
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getY() - 1 >= 0
							&& !(player1.getX() == player2.getX() && player1.getY() - 1 == player2.getY())) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player up
						player1.moveUp();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), -1);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player2.getY() - 1 >= 0
							&& !(player1.getX() == player2.getX() && player1.getY() == player2.getY() - 1)) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player up
						player2.moveUp();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), -2);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			case "s":
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getY() + 1 < board.getSizeY()
							&& !(player1.getX() == player2.getX() && player1.getY() + 1 == player2.getY())) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player down
						player1.moveDown();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), -1);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player2.getY() + 1 < board.getSizeY()
							&& !(player1.getX() == player2.getX() && player1.getY() == player2.getY() + 1)) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player down
						player2.moveDown();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), -2);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			case "a":
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getX() - 1 >= 0
							&& !(player1.getX() - 1 == player2.getX() && player1.getY() == player2.getY())) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player left
						player1.moveLeft();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), -1);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player2.getX() - 1 >= 0
							&& !(player1.getX() == player2.getX() - 1 && player1.getY() == player2.getY())) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player left
						player2.moveLeft();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), -2);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			case "d":
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getX() + 1 < board.getSizeX()
							&& !(player1.getX() + 1 == player2.getX() && player1.getY() == player2.getY())) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player left
						player1.moveRight();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), -1);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player2.getX() + 1 < board.getSizeX()
							&& !(player1.getX() == player2.getX() + 1 && player1.getY() == player2.getY())) {
						// saves the old position of the player and sets it to 0
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.setPlayer(oldPositionX, oldPositionY, 0);
						// moves the player left
						player2.moveRight();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), -2);
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			}
			// prints the score on the screen
			System.out.print("Score B: " + player1.getScore() + " Score W: " + player2.getScore() + " | ");
			// if scores of the players are below the score limit prints on the
			// screen which players turn it is
			if (player1.getActive() && player1.getScore() < 100 && player2.getScore() < 100) {
				System.out.println("Black to move");
			} else if (player2.getActive() && player1.getScore() < 100 && player2.getScore() < 100) {
				System.out.println("White to move");
			} else {
				// otherwise prints on the screen who won
				if (player1.getScore() >= 100) {
					System.out.println("Black wins");
				} else {
					System.out.println("White wins");
				}
			}
			// prints the board on the screen
			board.show();
		}
	}

	/**
	 * switches player turns
	 */
	private static void togglePlayer() {
		player1.toggleActive();
		player2.toggleActive();
	}

}
