
public class Player {

	private String color;
	private int x;
	private int y;

	public Player(String color, int x, int y) {
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

}
