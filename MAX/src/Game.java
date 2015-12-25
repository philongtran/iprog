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
	private Display display;
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
		boolean playAgain = true;

		while (playAgain) {
			// runs while players are below score
			while (!checkScore()) {

				for (int i = 0; i < player.length; i++) {
					// checks if score limit is reached
					if (checkScore()) {
						break;
					}
					// reads keyboard input to move the active player
					Direction direction = Direction.of(IO.promptAndRead("i: ").toLowerCase().substring(0, 1));
					// temporary variable to hold current player
					Player currentPlayer = player[i];
					// cases which are allowed
					switch (direction) {

					case UP:
						if (check(currentPlayer, direction)) {
							removePlayerFromPreviousPosition(currentPlayer);
							currentPlayer.moveUp();
							calcSet(i);
							break;
						} else {
							i = playerRetry(i);
							break;
						}

					case DOWN:
						if (check(currentPlayer, direction)) {
							removePlayerFromPreviousPosition(currentPlayer);
							currentPlayer.moveDown();
							calcSet(i);
							break;
						} else {
							i = playerRetry(i);
							break;
						}

					case LEFT:
						if (check(currentPlayer, direction)) {
							removePlayerFromPreviousPosition(currentPlayer);
							currentPlayer.moveLeft();
							calcSet(i);
							break;
						} else {
							i = playerRetry(i);
							break;
						}

					case RIGHT:
						if (check(currentPlayer, direction)) {
							removePlayerFromPreviousPosition(currentPlayer);
							currentPlayer.moveRight();
							calcSet(i);
							break;
						} else {
							i = playerRetry(i);
							break;
						}

					case RESTART:
						initializeGame();
						run();
						break;

					case NEW:
						initializeGame();
						run();
						break;

					case QUIT:
						System.exit(0);

					default:
						i = playerRetry(i);
						break;
					}
					// displays score and board
					display.show(i, checkScore());
				}
			}
			String direction = IO.promptAndRead("a? ").toLowerCase().substring(0, 1);
			switch (direction) {
			case "y":
				initializeGame();
				run();
			default:
				playAgain = false;
				break;
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
		this.display = new Display(player, board);
		display.show(-1, checkScore());
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
	private boolean oobCheck(Player player, Direction direction) {
		if (direction.equals(Direction.UP) && player.getY() > 0) {
			return true;
		}
		if (direction.equals(Direction.DOWN) && player.getY() + 1 < board.getSizeY()) {
			return true;
		}
		if (direction.equals(Direction.LEFT) && player.getX() > 0) {
			return true;
		}
		if (direction.equals(Direction.RIGHT) && player.getX() + 1 < board.getSizeX()) {
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
	private boolean collisionCheck(Player player, Direction direction) {
		if (direction.equals(Direction.UP) && board.getValue(player.getX(), player.getY() - 1) >= 0) {
			return true;
		}
		if (direction.equals(Direction.DOWN) && board.getValue(player.getX(), player.getY() + 1) >= 0) {
			return true;
		}
		if (direction.equals(Direction.LEFT) && board.getValue(player.getX() - 1, player.getY()) >= 0) {
			return true;
		}
		if (direction.equals(Direction.RIGHT) && board.getValue(player.getX() + 1, player.getY()) >= 0) {
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
	private boolean check(Player player, Direction direction) {
		return oobCheck(player, direction) && collisionCheck(player, direction);
	}

	/**
	 * Returns to the same player if move was illegal.
	 * 
	 * @param playerID
	 *            - ID of the wrongly assumed player.
	 * @return - The old player ID
	 */
	private int playerRetry(int playerID) {
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
	 * Checks if the score limit is reached.
	 * 
	 * @return - Boolean true if score limit is reached
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
