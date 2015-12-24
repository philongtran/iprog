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
					removePlayerFromPreviousPosition(player[i]);
					// moves the player up
					player[i].moveUp();
					// add the value of the board to the score
					player[i].addScore(board.getValue(player[i].getX(), player[i].getY()));
					// marks the field to be player owned
					board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
					break;

				case DOWN:
					removePlayerFromPreviousPosition(player[i]);
					player[i].moveDown();
					// add the value of the board to the score
					player[i].addScore(board.getValue(player[i].getX(), player[i].getY()));
					// marks the field to be player owned
					board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
					break;

				case LEFT:
					removePlayerFromPreviousPosition(player[i]);
					player[i].moveLeft();
					// add the value of the board to the score
					player[i].addScore(board.getValue(player[i].getX(), player[i].getY()));
					// marks the field to be player owned
					board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
					break;

				case RIGHT:
					removePlayerFromPreviousPosition(player[i]);
					player[i].moveRight();
					// add the value of the board to the score
					player[i].addScore(board.getValue(player[i].getX(), player[i].getY()));
					// marks the field to be player owned
					board.setPlayer(player[i].getX(), player[i].getY(), player[i].getColor());
					break;

				default:
					break;
				}
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
						board.show();
						break;
					} else {
						System.out.println("White wins");
						board.show();
						break;
					}
				}
				// prints the board on the screen
				board.show();
			}
		}
	}

	private void initializeGame() {
		// set starting position and color of the players and who's active
		playerPosition = new PlayerPosition(playerCount, boardSizeX, boardSizeY);
		int[][] position = playerPosition.getPosition();
		for (int i = 0; i < playerCount; i++) {
			player[i] = new Player(position[i][0], position[i][1], (i + 1) * (-1));
		}
		player[0].setActive(true);
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

}
