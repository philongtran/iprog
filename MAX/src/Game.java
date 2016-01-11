/**
 * This class represents the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */

public class Game {
	private final int SCORE_LIMIT = 5;
	private final int PLAYER_COUNT;
	private final int BOARD_SIZE_X;
	private final int BOARD_SIZE_Y;
	private Player[] player;
	private Board board;
	private Board clonedBoard;
	private PlayerPosition playerPosition;
	private Display display;
	private boolean restart;

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
		this.restart = false;
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
				Action action = Action.of(IO.promptAndRead("i: ").toLowerCase().substring(0, 1));
				// temporary variable to hold current player
				Player currentPlayer = player[i];
				// cases which are allowed
				switch (action) {
				case UP:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case DOWN:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case LEFT:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case RIGHT:
					if (canMoveInDirection(currentPlayer, action)) {
						move(currentPlayer, action);
					} else {
						i = playerRetry(i);
					}
					break;
				case HELP:
					IO.writeln("Up: w | Down: s | Left: a | Right: d | Restart: r | New game: n | Quit: q | Help: h");
					IO.promptAndRead("Press any key to continue.");
					i = playerRetry(i);
					break;
				case RESTART:
					restart = true;
					initializeGame();
					i = -1;
					break;
				case NEW:
					initializeGame();
					i = -1;
					break;
				case QUIT:
					return;
				default:
					i = playerRetry(i);
					break;
				}
				// displays score and board
				display.draw(i, checkScore());
			}
		}
		restart = IO.promptAndRead("again? Type Y for yes or N for no").toLowerCase().substring(0, 1).equals("y");
		if (restart) {
			run();
		}
		return;
	}

	private void move(Player currentPlayer, Action direction) {
		removePlayerFromPreviousPosition(currentPlayer);
		switch (direction) {
		case DOWN:
			currentPlayer.moveDown();
			break;
		case LEFT:
			currentPlayer.moveLeft();
			break;
		case RIGHT:
			currentPlayer.moveRight();
			break;
		case UP:
			currentPlayer.moveUp();
			break;
		default:
			break;
		}
		addScoreAndSetNewPosition(currentPlayer);
	}

	/**
	 * Initializes the game with its default values.
	 * 
	 * @throws CloneNotSupportedException
	 */
	private void initializeGame() throws CloneNotSupportedException {
		// set starting positions
		playerPosition = new PlayerPosition(BOARD_SIZE_X, BOARD_SIZE_Y, PLAYER_COUNT);
		int[][] position = playerPosition.getPosition();
		// create players with starting location and color
		for (int i = 0; i < PLAYER_COUNT; i++) {
			player[i] = new Player(position[i][0], position[i][1], (i + 1) * (-1));
		}
		try {
			// restart game logic
			if (restart) {
				restart = false;
				board = clonedBoard.clone();
			} else {
				// initialize the board and its size
				board = new Board(BOARD_SIZE_X, BOARD_SIZE_Y);
				clonedBoard = board.clone();
			}
		} catch (CloneNotSupportedException e) {
			IO.writeln("error in cloning");
		}
		// plants the players on the board
		for (int i = 0; i < player.length; i++) {
			board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
		}
		// displays score and board
		this.display = new Display(player, board);
		display.draw(-1, checkScore());
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
	 * @return - Returns boolean true if move is legitimate
	 */
	private boolean oobCheck(Player player, Action direction) {
		if (direction.equals(Action.UP) && player.getY() > 0) {
			return true;
		}
		if (direction.equals(Action.DOWN) && player.getY() + 1 < board.getSizeY()) {
			return true;
		}
		if (direction.equals(Action.LEFT) && player.getX() > 0) {
			return true;
		}
		if (direction.equals(Action.RIGHT) && player.getX() + 1 < board.getSizeX()) {
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
	 * @return - Returns boolean true if move is legitimate
	 */
	private boolean collisionCheck(Player player, Action direction) {
		switch (direction) {
		case DOWN:
			return board.getValue(player.getX(), player.getY() + 1) >= 0;
		case LEFT:
			return board.getValue(player.getX() - 1, player.getY()) >= 0;
		case RIGHT:
			return board.getValue(player.getX() + 1, player.getY()) >= 0;
		case UP:
			return board.getValue(player.getX(), player.getY() - 1) >= 0;
		default:
			return false;
		}
	}

	/**
	 * Checks for out of bounds and player collision problems.
	 * 
	 * @param player
	 *            - Which player should be tested
	 * @param direction
	 *            - Which direction does the player want to go
	 * @return - Returns boolean true if move is legitimate
	 */
	private boolean canMoveInDirection(Player player, Action direction) {
		return oobCheck(player, direction) && collisionCheck(player, direction);
	}

	/**
	 * Returns to the same player if move was illegal.
	 * 
	 * @param playerID
	 *            - ID of the wrongly assumed player.
	 * @return - Returns the old player ID
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
	 * @param currentPlayer
	 *            - player whos turn it is
	 */
	private void addScoreAndSetNewPosition(Player currentPlayer) {
		// add the value of the board to the score
		int x = currentPlayer.getX();
		int y = currentPlayer.getY();

		currentPlayer.addScore(board.getValue(x, y));
		// marks the field to be player owned
		board.setPlayer(x, y, currentPlayer.getColor());
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
