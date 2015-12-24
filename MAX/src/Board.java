import java.util.Random;

/**
 * This class represents the board of the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */
public class Board {
	private int minimumNumber; // minimum number which can appear on the board
	private int maximumNumber; // maximum number which can appear on the board
	private final int SIZE_X; // size of the x side board
	private final int SIZE_Y; // size of the y side board
	private int[][] board; // board itself
	private Random random; // random number generator

	/**
	 * The Constructor creates a board of the size x,y and initializes it.
	 * 
	 * @param sizeX
	 *            - Sets the size of the x part
	 * @param sizeY
	 *            - Sets the size of the y part
	 * 
	 */
	public Board(int sizeX, int sizeY) {
		this.minimumNumber = 1;
		this.maximumNumber = 9;
		this.SIZE_X = sizeX;
		this.SIZE_Y = sizeY;
		this.board = new int[sizeY][sizeX];
		this.random = new Random();
		this.initialize();
	}

	/**
	 * Initializes the fields of the board with random numbers.
	 */
	public void initialize() {
		for (int y = 0; y < SIZE_Y; y++) {
			for (int x = 0; x < SIZE_X; x++) {
				board[y][x] = random.nextInt((maximumNumber - minimumNumber) + 1) + minimumNumber;
			}
		}
	}

	/**
	 * Assigns a value to the x,y coordinate.
	 * 
	 * @param x
	 *            - Selects the x part of the board
	 * @param y
	 *            - Selects the y part of the board
	 * @param value
	 *            - Enter the value in the x,y coordinate
	 * 
	 */
	public void setPlayer(int x, int y, int value) {
		this.board[y][x] = value;
	}

	/**
	 * Getting a specific value in the x,y coordinate.
	 * 
	 * @param x
	 *            - X coordinate of the board
	 * @param y
	 *            - Y coordinate of the board
	 * @return - Returns the value in the x,y coordinate
	 * 
	 */
	public int getValue(int x, int y) {
		return this.board[y][x];
	}

	/**
	 * Returns the x size of the board.
	 * 
	 * @return - Returns the x size of the board
	 * 
	 */
	public int getSizeX() {
		return this.SIZE_X;
	}

	/**
	 * Returns the y size of the board.
	 * 
	 * @return - Returns the y size of the board
	 * 
	 */
	public int getSizeY() {
		return this.SIZE_Y;
	}

	/**
	 * Assigning letters from the colors to the players.
	 * 
	 * @param color
	 *            - Color of the player
	 * @return - Letter of the player if available, otherwise generic name
	 */
	public String returnLetter(int color) {
		switch (color) {
		case -1:
			return "B";
		case -2:
			return "W";
		case -3:
			return "G";
		case -4:
			return "L";
		default:
			return "P" + (color * (-1));
		}
	}

	/**
	 * Displays the formated board on the screen. It shows its values and spots
	 * the players and then assigns them with a predefined letter or generic
	 * name.
	 */
	public void showBoard() {
		for (int y = 0; y < SIZE_Y; y++) {
			for (int x = 0; x < SIZE_X; x++) {
				if (getValue(x, y) < 0) {
					System.out.print(returnLetter(getValue(x, y)) + " ");
				} else {
					System.out.print(getValue(x, y) + " ");
				}

			}
			System.out.println();
		}
	}

}
