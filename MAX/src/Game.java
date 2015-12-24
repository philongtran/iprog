
public class Game {
	private Player player1;
	private Player player2;
	private Board board;
	private PlayerPosition playerPosition;
	private final int SCORELIMIT = 105;
	private int boardSizeX;
	private int boardSizeY;

	/**
	 * constructor
	 * 
	 * @param boardSizeX
	 * @param boardSizeY
	 */
	public Game(int boardSizeX, int boardSizeY) {
		this.boardSizeX = boardSizeX;
		this.boardSizeY = boardSizeY;
	}

	/**
	 * method for running the game
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		initializeGame();

		// runs while players are below score
		while (player1.getScore() < SCORELIMIT && player2.getScore() < SCORELIMIT) {
			// reads keyboard input to move the active player
			Direction direction = Direction.of(IO.promptAndRead("i: "));
			// cases which are allowed
			switch (direction) {
			// moves the player up
			case UP:
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getY() - 1 >= 0
							&& !(player1.getX() == player2.getX() && player1.getY() - 1 == player2.getY())) {
						removePlayerFromPreviousPosition(player1);
						// moves the player up
						player1.moveUp();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), player1.getColor());
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
						removePlayerFromPreviousPosition(player2);
						// moves the player up
						player2.moveUp();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), player2.getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			case DOWN:
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getY() + 1 < board.getSizeY()
							&& !(player1.getX() == player2.getX() && player1.getY() + 1 == player2.getY())) {
						removePlayerFromPreviousPosition(player1);
						player1.moveDown();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), player1.getColor());
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
						removePlayerFromPreviousPosition(player2);
						player2.moveDown();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), player2.getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			case LEFT:
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getX() - 1 >= 0
							&& !(player1.getX() - 1 == player2.getX() && player1.getY() == player2.getY())) {
						removePlayerFromPreviousPosition(player1);
						player1.moveLeft();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), player1.getColor());
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
						removePlayerFromPreviousPosition(player2);
						player2.moveLeft();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), player2.getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			case RIGHT:
				// checks which players turn it is
				if (player1.getActive()) {
					// out of bounds and collision check
					if (player1.getX() + 1 < board.getSizeX()
							&& !(player1.getX() + 1 == player2.getX() && player1.getY() == player2.getY())) {
						removePlayerFromPreviousPosition(player1);
						player1.moveRight();
						// add the value of the board to the score
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						// marks the field to be player owned
						board.setPlayer(player1.getX(), player1.getY(), player1.getColor());
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
						removePlayerFromPreviousPosition(player2);
						player2.moveRight();
						// add the value of the board to the score
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						// marks the field to be player owned
						board.setPlayer(player2.getX(), player2.getY(), player2.getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				}
				break;
			default:
				break;
			}
			// prints the score on the screen
			System.out.print("Score B: " + player1.getScore() + " Score W: " + player2.getScore() + " | ");
			// if scores of the players are below the score limit prints on the
			// screen which players turn it is
			if (player1.getActive() && player1.getScore() < SCORELIMIT && player2.getScore() < SCORELIMIT) {
				System.out.println("Black to move");
			} else if (player2.getActive() && player1.getScore() < SCORELIMIT && player2.getScore() < SCORELIMIT) {
				System.out.println("White to move");
			} else {
				// otherwise prints on the screen who won
				if (player1.getScore() >= SCORELIMIT) {
					System.out.println("Black wins");
				} else {
					System.out.println("White wins");
				}
			}
			// prints the board on the screen
			board.show();
		}
	}

	private void initializeGame() {
		// set starting position and color of the players and who's active
		playerPosition = new PlayerPosition(2, boardSizeX, boardSizeY);
		int[][] position = playerPosition.getPosition();
		player1 = new Player(position[0][0], position[0][1], -1);
		player1.setActive(true);
		player2 = new Player(position[1][0], position[1][1], -2);
		player2.setActive(false);
		// initialize the board and its size
		board = new Board(boardSizeX, boardSizeY);
		// plants the players on the board
		board.setPlayer(player1.getX(), player1.getY(), player1.getColor());
		board.setPlayer(player2.getX(), player2.getY(), player2.getColor());
		// prints the score on the screen and which players turn it is
		System.out.print("Score B: " + player1.getScore() + " Score W: " + player2.getScore() + " | ");
		System.out.println("Black to move");
		// prints the board on the screen
		board.show();
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
	 * switches player turns
	 */
	private void togglePlayer() {
		player1.toggleActive();
		player2.toggleActive();
	}

}
