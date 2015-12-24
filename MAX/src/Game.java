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
	private int playerCount;
	private Board board;
	private PlayerPosition playerPosition;
	private final int SCORELIMIT = 20;
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
		while (player[0].getScore() < SCORELIMIT && player[1].getScore() < SCORELIMIT) {

			for (int i = 0; i < player.length; i++) {
				// reads keyboard input to move the active player
				Direction direction = Direction.of(IO.promptAndRead("i: "));
				// cases which are allowed
				switch (direction) {

				case UP:
					if (oobCheck(player[i], "UP") && collisionCheck(player[i], "UP")) {
						removePlayerFromPreviousPosition(player[i]);
						// moves the player up
						player[i].moveUp();
						calculate(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				case DOWN:
					if (oobCheck(player[i], "DOWN") && collisionCheck(player[i], "DOWN")) {
						removePlayerFromPreviousPosition(player[i]);
						player[i].moveDown();
						// add the value of the board to the score
						calculate(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				case LEFT:
					if (oobCheck(player[i], "LEFT") && collisionCheck(player[i], "LEFT")) {
						removePlayerFromPreviousPosition(player[i]);
						player[i].moveLeft();
						// add the value of the board to the score
						calculate(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				case RIGHT:
					if (oobCheck(player[i], "RIGHT") && collisionCheck(player[i], "RIGHT")) {
						removePlayerFromPreviousPosition(player[i]);
						player[i].moveRight();
						calculate(i);
						break;
					} else {
						i = previousPlayer(i);
						break;
					}

				default:
					i = previousPlayer(i);
					break;
				}
				display(i);
			}
		}
	}

	private void initializeGame() {
		// set starting position and color of the players
		playerPosition = new PlayerPosition(playerCount, boardSizeX, boardSizeY);
		int[][] position = playerPosition.getPosition();
		for (int i = 0; i < playerCount; i++) {
			player[i] = new Player(position[i][0], position[i][1], (i + 1) * (-1));
		}
		// initialize the board and its size
		board = new Board(boardSizeX, boardSizeY);
		// plants the players on the board
		board.setPlayer(player[0].getX(), player[0].getY(), player[0].getColor());
		board.setPlayer(player[1].getX(), player[1].getY(), player[1].getColor());
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

	private int previousPlayer(int i) {
		if (i == 0) {
			return player.length - 1;
		} else {
			return --i;
		}
	}

	private void calculate(int i) {
		// add the value of the board to the score
		player[i].addScore(board.getValue(player[i].getX(), player[i].getY()));
		// marks the field to be player owned
		board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
	}

	private void display(int i) {
		// prints the score on the screen
		System.out.print("Score B: " + player[0].getScore() + " Score W: " + player[1].getScore() + " | ");
		// if scores of the players are below the score limit prints on
		// the
		// screen which players turn it is
		if (i == 1 && player[0].getScore() < SCORELIMIT && player[1].getScore() < SCORELIMIT) {
			System.out.println("Black to move");
		} else if (i == 0 && player[0].getScore() < SCORELIMIT && player[1].getScore() < SCORELIMIT) {
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
