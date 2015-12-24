/**
 * This class represents the game.
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
	private final int SCORE_LIMIT = 10;
	private final int PLAYER_COUNT;
	private final int BOARD_SIZE_X;
	private final int BOARD_SIZE_Y;

	/**
	 * The constructor of the game.
	 * 
	 * @param boardSizeX
	 *            - X size of the board
	 * @param boardSizeY
	 *            - Y size of the board
	 * @param playerCount
	 *            - Amount of players
	 */
	public Game(int boardSizeX, int boardSizeY, int playerCount) {
		this.BOARD_SIZE_X = boardSizeX;
		this.BOARD_SIZE_Y = boardSizeY;
		this.PLAYER_COUNT = playerCount;
		this.player = new Player[playerCount];
	}

	/**
	 * Method for running the game logic. It contains the movement and score-
	 * and board display.
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
	 * Initializes the game with its default values.
	 */
	private void initializeGame() {
		// set starting positions
		playerPosition = new PlayerPosition(BOARD_SIZE_X, BOARD_SIZE_Y, PLAYER_COUNT);
		int[][] position = playerPosition.getPosition();
		// create players with starting location and color
		for (int i = 0; i < PLAYER_COUNT; i++) {
			player[i] = new Player(position[i][0], position[i][1], (i + 1) * (-1));
		}
		// initialize the board and its size
		board = new Board(BOARD_SIZE_X, BOARD_SIZE_Y);
		// plants the players on the board
		for (int i = 0; i < player.length; i++) {
			board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
		}
		// displays score and board
		display(-1);
	}

	/**
	 * Gets the old position of the player and sets the value inside the board
	 * to 0.
	 * 
	 * @param player
	 *            - Which player position should be reset to 0
	 */
	private void removePlayerFromPreviousPosition(Player player) {
		board.setPlayer(player.getX(), player.getY(), 0);
	}

	/**
	 * Checking if player tries to move out of bounds.
	 * 
	 * @param player
	 *            - Which player should be tested
	 * @param direction
	 *            - Which direction does the player want to go
	 * @return - Returns boolean if move is legitimate
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
	 * Checking player movement for collision with other players.
	 * 
	 * @param player
	 *            - Which player should be tested
	 * @param direction
	 *            - Which direction does the player want to go
	 * @return - Returns boolean if move is legitimate
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
	 * Checks for out of bounds and player collision problems.
	 * 
	 * @param player
	 *            - Which player should be tested
	 * @param direction
	 *            - Which direction does the player want to go
	 * @return - Returns boolean if move is legitimate
	 */
	private boolean check(Player player, String direction) {
		return oobCheck(player, direction) && collisionCheck(player, direction);
	}

	/**
	 * Returns to previous/same player if move were illegal.
	 * 
	 * @param playerID
	 *            - ID of the wrongly assumed player.
	 * @return - The old player ID
	 */
	private int previousPlayer(int playerID) {
		if (playerID == 0) {
			return player.length - 1;
		} else {
			return --playerID;
		}
	}

	/**
	 * Makes the calculation of the score and sets the players new position.
	 * 
	 * @param playerID
	 *            - ID of the player which score and position should be set
	 */
	private void calcSet(int playerID) {
		// add the value of the board to the score
		player[playerID].addScore(board.getValue(player[playerID].getX(), player[playerID].getY()));
		// marks the field to be player owned
		board.setPlayer(player[playerID].getX(), player[playerID].getY(), player[playerID].getColor());
	}

	/**
	 * Displays the score board and the play board to the screen.
	 * 
	 * @param playerID
	 */
	private void display(int playerID) {
		String score = "";
		for (int i = 0; i < player.length; i++) {
			score += "Score " + board.returnLetter(player[i].getColor()) + ": " + player[i].getScore() + " ";
		}
		if (checkScore()) {
			score += "| " + board.returnLetter(player[playerID].getColor()) + " wins";
		} else {
			if (playerID == player.length - 1) {
				score += "| " + board.returnLetter(player[0].getColor()) + " to move";
			} else {
				score += "| " + board.returnLetter(player[playerID + 1].getColor()) + " to move";
			}
		}
		IO.writeln(score);
		board.showBoard();
	}

	/**
	 * Checks if the score limit is reached.
	 * 
	 * @return - Boolean if score limit is reached
	 */
	private boolean checkScore() {
		boolean scoreReached = false;
		for (int i = 0; i < player.length; i++) {
			if (player[i].getScore() >= SCORE_LIMIT) {
				scoreReached = true;
			}
		}
		return scoreReached;
	}

}
