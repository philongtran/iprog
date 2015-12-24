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
	private final int SCORELIMIT = 105;
	private int boardSizeX;
	private int boardSizeY;

	/**
	 * constructor
	 * 
	 * @param boardSizeX
	 * @param boardSizeY
	 */
	public Game(int boardSizeX, int boardSizeY, int playerCount) {
		this.boardSizeX = boardSizeX;
		this.boardSizeY = boardSizeY;
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
		while (player[0].getScore() < SCORELIMIT && player[1].getScore() < SCORELIMIT) {
			// reads keyboard input to move the active player
			Direction direction = Direction.of(IO.promptAndRead("i: "));
			// cases which are allowed
			switch (direction) {
			// moves the player up
			case UP:
				// checks which players turn it is
				if (player[0].getActive()) {
					// out of bounds and collision check
					if (player[0].getY() - 1 >= 0
							&& !(player[0].getX() == player[1].getX() && player[0].getY() - 1 == player[1].getY())) {
						removePlayerFromPreviousPosition(player[0]);
						// moves the player up
						player[0].moveUp();
						// add the value of the board to the score
						player[0].addScore(board.getValue(player[0].getX(), player[0].getY()));
						// marks the field to be player owned
						board.setPlayer(player[0].getX(), player[0].getY(), player[0].getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player[1].getY() - 1 >= 0
							&& !(player[0].getX() == player[1].getX() && player[0].getY() == player[1].getY() - 1)) {
						removePlayerFromPreviousPosition(player[1]);
						// moves the player up
						player[1].moveUp();
						// add the value of the board to the score
						player[1].addScore(board.getValue(player[1].getX(), player[1].getY()));
						// marks the field to be player owned
						board.setPlayer(player[1].getX(), player[1].getY(), player[1].getColor());
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
				if (player[0].getActive()) {
					// out of bounds and collision check
					if (player[0].getY() + 1 < board.getSizeY()
							&& !(player[0].getX() == player[1].getX() && player[0].getY() + 1 == player[1].getY())) {
						removePlayerFromPreviousPosition(player[0]);
						player[0].moveDown();
						// add the value of the board to the score
						player[0].addScore(board.getValue(player[0].getX(), player[0].getY()));
						// marks the field to be player owned
						board.setPlayer(player[0].getX(), player[0].getY(), player[0].getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player[1].getY() + 1 < board.getSizeY()
							&& !(player[0].getX() == player[1].getX() && player[0].getY() == player[1].getY() + 1)) {
						// saves the old position of the player and sets it to 0
						removePlayerFromPreviousPosition(player[1]);
						player[1].moveDown();
						// add the value of the board to the score
						player[1].addScore(board.getValue(player[1].getX(), player[1].getY()));
						// marks the field to be player owned
						board.setPlayer(player[1].getX(), player[1].getY(), player[1].getColor());
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
				if (player[0].getActive()) {
					// out of bounds and collision check
					if (player[0].getX() - 1 >= 0
							&& !(player[0].getX() - 1 == player[1].getX() && player[0].getY() == player[1].getY())) {
						removePlayerFromPreviousPosition(player[0]);
						player[0].moveLeft();
						// add the value of the board to the score
						player[0].addScore(board.getValue(player[0].getX(), player[0].getY()));
						// marks the field to be player owned
						board.setPlayer(player[0].getX(), player[0].getY(), player[0].getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player[1].getX() - 1 >= 0
							&& !(player[0].getX() == player[1].getX() - 1 && player[0].getY() == player[1].getY())) {
						removePlayerFromPreviousPosition(player[1]);
						player[1].moveLeft();
						// add the value of the board to the score
						player[1].addScore(board.getValue(player[1].getX(), player[1].getY()));
						// marks the field to be player owned
						board.setPlayer(player[1].getX(), player[1].getY(), player[1].getColor());
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
				if (player[0].getActive()) {
					// out of bounds and collision check
					if (player[0].getX() + 1 < board.getSizeX()
							&& !(player[0].getX() + 1 == player[1].getX() && player[0].getY() == player[1].getY())) {
						removePlayerFromPreviousPosition(player[0]);
						player[0].moveRight();
						// add the value of the board to the score
						player[0].addScore(board.getValue(player[0].getX(), player[0].getY()));
						// marks the field to be player owned
						board.setPlayer(player[0].getX(), player[0].getY(), player[0].getColor());
						// switches turns
						togglePlayer();
					} else {
						// if out of bounds or player collision prompt player
						// again to move
						break;
					}
				} else {
					// out of bounds and collision check
					if (player[1].getX() + 1 < board.getSizeX()
							&& !(player[0].getX() == player[1].getX() + 1 && player[0].getY() == player[1].getY())) {
						removePlayerFromPreviousPosition(player[1]);
						player[1].moveRight();
						// add the value of the board to the score
						player[1].addScore(board.getValue(player[1].getX(), player[1].getY()));
						// marks the field to be player owned
						board.setPlayer(player[1].getX(), player[1].getY(), player[1].getColor());
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
			System.out.print("Score B: " + player[0].getScore() + " Score W: " + player[1].getScore() + " | ");
			// if scores of the players are below the score limit prints on the
			// screen which players turn it is
			if (player[0].getActive() && player[0].getScore() < SCORELIMIT && player[1].getScore() < SCORELIMIT) {
				System.out.println("Black to move");
			} else if (player[1].getActive() && player[0].getScore() < SCORELIMIT
					&& player[1].getScore() < SCORELIMIT) {
				System.out.println("White to move");
			} else {
				// otherwise prints on the screen who won
				if (player[0].getScore() >= SCORELIMIT) {
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
		player[0] = new Player(position[0][0], position[0][1], -1);
		player[0].setActive(true);
		player[1] = new Player(position[1][0], position[1][1], -2);
		player[1].setActive(false);
		// initialize the board and its size
		board = new Board(boardSizeX, boardSizeY);
		// plants the players on the board
		board.setPlayer(player[0].getX(), player[0].getY(), player[0].getColor());
		board.setPlayer(player[1].getX(), player[1].getY(), player[1].getColor());
		// prints the score on the screen and which players turn it is
		System.out.print("Score B: " + player[0].getScore() + " Score W: " + player[1].getScore() + " | ");
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
		player[0].toggleActive();
		player[1].toggleActive();
	}

}
