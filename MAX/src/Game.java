/**
 * this class represents the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class Game {
	private Player[] player;
	private Board board;
	private PlayerPosition playerPosition;
	private final int SCORELIMIT = 10;
	private final int playerCount;
	private final int boardSizeX;
	private final int boardSizeY;

	/**
	 * constructor
	 * 
	 * @param boardSizeX
	 * @param boardSizeY
	 */
	public Game(int boardSizeX, int boardSizeY, int playerCount) {
		this.boardSizeX = boardSizeX;
		this.boardSizeY = boardSizeY;
		this.playerCount = playerCount;
		this.player = new Player[playerCount];
	}

	/**
	 * method for running the game
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		initializeGame();

		// runs while players are below score
		while (!checkScore()) {

			for (int i = 0; i < player.length; i++) {
				// checks if score limit is reached
				if (checkScore()) {
					break;
				}
				// reads keyboard input to move the active player
				Direction direction = Direction.of(IO.promptAndRead("i: "));
				// cases which are allowed
				switch (direction) {

				case UP:
					if (check(player[i], "UP")) {
						removePlayerFromPreviousPosition(player[i]);
						player[i].moveUp();
						calcSet(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				case DOWN:
					if (check(player[i], "DOWN")) {
						removePlayerFromPreviousPosition(player[i]);
						player[i].moveDown();
						calcSet(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				case LEFT:
					if (check(player[i], "LEFT")) {
						removePlayerFromPreviousPosition(player[i]);
						player[i].moveLeft();
						calcSet(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				case RIGHT:
					if (check(player[i], "RIGHT")) {
						removePlayerFromPreviousPosition(player[i]);
						player[i].moveRight();
						calcSet(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				default:
					i = previousPlayer(i);
					break;
				}
				// displays score and board
				display(i);
			}
		}
	}

	/**
	 * initializes the game with its default values
	 */
	private void initializeGame() {
		// set starting positions
		playerPosition = new PlayerPosition(boardSizeX, boardSizeY, playerCount);
		int[][] position = playerPosition.getPosition();
		// create players with starting location and color
		for (int i = 0; i < playerCount; i++) {
			player[i] = new Player(position[i][0], position[i][1], (i + 1) * (-1));
		}
		// initialize the board and its size
		board = new Board(boardSizeX, boardSizeY);
		// plants the players on the board
		board.setPlayer(player[0].getX(), player[0].getY(), player[0].getColor());
		board.setPlayer(player[1].getX(), player[1].getY(), player[1].getColor());
		// displays score and board
		display(1);
	}

	/**
	 * saves the old position of the player and sets it to 0
	 * 
	 * @param player
	 *            which position should be reset to 0
	 */
	private void removePlayerFromPreviousPosition(Player player) {
		int oldPositionX = player.getX();
		int oldPositionY = player.getY();
		board.setPlayer(oldPositionX, oldPositionY, 0);
	}

	/**
	 * out of bounds check
	 * 
	 * @param player
	 * @param direction
	 * @return
	 */
	private boolean oobCheck(Player player, String direction) {
		if (direction.equals("UP") && player.getY() > 0) {
			return true;
		}
		if (direction.equals("DOWN") && player.getY() + 1 < board.getSizeY()) {
			return true;
		}
		if (direction.equals("LEFT") && player.getX() > 0) {
			return true;
		}
		if (direction.equals("RIGHT") && player.getX() + 1 < board.getSizeX()) {
			return true;
		}
		return false;
	}

	/**
	 * player collision check
	 * 
	 * @param player
	 * @param direction
	 * @return
	 */
	private boolean collisionCheck(Player player, String direction) {
		if (direction.equals("UP") && board.getValue(player.getX(), player.getY() - 1) >= 0) {
			return true;
		}
		if (direction.equals("DOWN") && board.getValue(player.getX(), player.getY() + 1) >= 0) {
			return true;
		}
		if (direction.equals("LEFT") && board.getValue(player.getX() - 1, player.getY()) >= 0) {
			return true;
		}
		if (direction.equals("RIGHT") && board.getValue(player.getX() + 1, player.getY()) >= 0) {
			System.out.println(board.getValue(player.getX(), player.getX() + 1));
			return true;
		}
		return false;
	}

	/**
	 * checks the out of bounds and player collision
	 * 
	 * @param player
	 * @param direction
	 * @return
	 */
	private boolean check(Player player, String direction) {
		return oobCheck(player, direction) && collisionCheck(player, direction);
	}

	/**
	 * returns to previous player
	 * 
	 * @param i
	 * @return
	 */
	private int previousPlayer(int i) {
		if (i == 0) {
			return player.length - 1;
		} else {
			return --i;
		}
	}

	/**
	 * makes calculation of the score and sets players new position
	 * 
	 * @param i
	 */
	private void calcSet(int i) {
		// add the value of the board to the score
		player[i].addScore(board.getValue(player[i].getX(), player[i].getY()));
		// marks the field to be player owned
		board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
	}

	/**
	 * DO NOT DELETE prints score board and play board to the screen <- for
	 * special use DO NOT DELETE
	 * 
	 * @param i
	 */
	/*
	 * private void display(int i) { // prints the score on the screen
	 * System.out.print("Score B: " + player[0].getScore() + " Score W: " +
	 * player[1].getScore() + " | "); // if scores of the players are below the
	 * score limit prints on // the // screen which players turn it is if (i ==
	 * 1 && player[0].getScore() < SCORELIMIT && player[1].getScore() <
	 * SCORELIMIT) { System.out.println("Black to move"); } else if (i == 0 &&
	 * player[0].getScore() < SCORELIMIT && player[1].getScore() < SCORELIMIT) {
	 * System.out.println("White to move"); } else { // otherwise prints on the
	 * screen who won if (player[0].getScore() >= SCORELIMIT) {
	 * System.out.println("Black wins"); } else { System.out.println(
	 * "White wins"); } } // prints the board on the screen board.show(); }
	 */

	/**
	 * prints score board and play board to the screen <- for generic use
	 * 
	 * @param i
	 */
	private void display(int i) {
		String score = "";
		for (int j = 0; j < player.length; j++) {
			score += "Score " + returnLetter(player[j].getColor()) + ": " + player[j].getScore() + " ";
		}
		if (checkScore()) {
			score += "| " + returnLetter(player[i].getColor()) + " wins";
		} else {
			if (i == 0) {
				score += "| " + returnLetter(player[player.length - 1].getColor()) + " to move";
			} else {
				score += "| " + returnLetter(player[i - 1].getColor()) + " to move";
			}
		}
		IO.writeln(score);
		showBoard();
	}

	/**
	 * checks if score limit is reached
	 * 
	 * @return
	 */
	private boolean checkScore() {
		boolean scoreReached = false;
		for (int i = 0; i < player.length; i++) {
			if (player[i].getScore() >= SCORELIMIT) {
				scoreReached = true;
			}
		}
		return scoreReached;
	}

	/**
	 * assignment letters to the players
	 * 
	 * @param color
	 * @return
	 */
	private String returnLetter(int color) {
		switch (color) {
		case -1:
			return "B";
		case -2:
			return "W";
		default:
			return "P" + (color * (-1));
		}
	}

	/**
	 * print the formated board on the screen it shows it values and spots the
	 * players and assign them a value a player can recognize
	 */
	private void showBoard() {
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				if (board.getValue(x, y) < 0) {
					System.out.print(returnLetter(board.getValue(x, y)) + " ");
				} else {
					System.out.print(board.getValue(x, y) + " ");
				}

			}
			System.out.println();
		}
	}

}
