
public class Player {

	private int color;
	private int x;
	private int y;

	public Player(int x, int y, int color) {
		this.color = color;
		this.color = color;
		this.x = x;
		this.y = y;
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

}
