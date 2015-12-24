/**
 * This class represents the player of the game.
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 *
 */
public class Player {

	private final int color; // color of the player (-1 = black; -2 = white)
	private int x; // x coordinate of the player
	private int y; // y coordinate of the player
	private int score; // score of the player

	/**
	 * constructor of the class to initialize the player
	 * 
	 * @param x
	 *            x coordinate of the player
	 * @param y
	 *            y coordinate of the player
	 * @param color
	 *            color of the player (negative number -1 = black; -2 = white)
	 * 
	 */
	public Player(int x, int y, int color) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.score = 0;
	}

	/**
	 * moves the player up one field
	 */
	public void moveUp() {
		y--;
	}

	/**
	 * moves the player down one field
	 */
	public void moveDown() {
		y++;
	}

	/**
	 * moves the player left one field
	 */
	public void moveLeft() {
		x--;
	}

	/**
	 * moves the player right one field
	 */
	public void moveRight() {
		x++;
	}

	/**
	 * gets the x coordinate of the player
	 */
	public int getX() {
		return x;
	}

	/**
	 * gets the y coordinate of the player
	 */
	public int getY() {
		return y;
	}

	/**
	 * gets the color of the player
	 */
	public int getColor() {
		return color;
	}

	/**
	 * 
	 * @param score
	 *            add the value of the score to the players score
	 * 
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 * 
	 * @return returns the score of the player
	 * 
	 */
	public int getScore() {
		return score;
	}

}
