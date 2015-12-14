
public class Player {

	private int color;
	private int x;
	private int y;
	private int score;

	public Player(int x, int y, int color) {
		this.color = color;
		this.color = color;
		this.x = x;
		this.y = y;
		this.score = 0;
	}

	public void moveUp() {
		y--;
	}

	public void moveDown() {
		y++;
	}

	public void moveLeft() {
		x--;
	}

	public void moveRight() {
		x++;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getColor() {
		return color;
	}

	public void addScore(int score) {
		this.score += score;
	}

}
