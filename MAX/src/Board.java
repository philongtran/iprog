import java.util.Random;

/**
 * this class represents the board of the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */
public class Board {
	private final int SIZE_X; // size of the x side board
	private final int SIZE_Y; // size of the y side board
	private int[][] board; // board itself
	private Random random; // random number generator

	/**
	 * 
	 * @param sizeX
	 *            sets the size of the x part
	 * @param sizeY
	 *            sets the size of the y part
	 * 
	 *            constructor creates a board of the size x,y and initialize it
	 * 
	 */
	public Board(int sizeX, int sizeY) {
		this.SIZE_X = sizeX;
		this.SIZE_Y = sizeY;
		this.board = new int[sizeY][sizeX];
		this.random = new Random();
		this.initialize();
	}

	/**
	 * initializes the fields of the board with random numbers
	 */
	public void initialize() {
		for (int y = 0; y < SIZE_Y; y++) {
			for (int x = 0; x < SIZE_X; x++) {
				board[y][x] = random.nextInt(9) + 1;
			}
		}
	}

	/**
	 * print the formated board on the screen it shows it values and spots the
	 * players and assign them a value a player can recognize
	 */
	/*
	 * public void show() { for (int y = 0; y < sizeY; y++) { for (int x = 0; x
	 * < sizeX; x++) { if (board[y][x] == -1) { System.out.print("B "); } else
	 * if (board[y][x] == -2) { System.out.print("W "); } else {
	 * System.out.print(board[y][x] + " "); }
	 * 
	 * } System.out.println(); } }
	 */

	/**
	 * 
	 * @param x
	 *            selects the x part of the board
	 * @param y
	 *            selects the y part of the board
	 * @param value
	 *            enter the value in the x,y coordinate
	 * 
	 */
	public void setPlayer(int x, int y, int value) {
		this.board[y][x] = value;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return returns the value in the x,y coordinate
	 * 
	 */
	public int getValue(int x, int y) {
		return this.board[y][x];
	}

	/**
	 * 
	 * @return returns the x size of the board
	 * 
	 */
	public int getSizeX() {
		return this.SIZE_X;
	}

	/**
	 * 
	 * @return returns the y size of the board
	 * 
	 */
	public int getSizeY() {
		return this.SIZE_Y;
	}

}
