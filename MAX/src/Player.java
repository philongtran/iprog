
public class Player {

	private String color;
	private int x;
	private int y;

	public Player(int color, int x, int y) {
		if (color == 1) {
			this.color = "w";
		}
		if (color == 0) {
			this.color = "b";
		}
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

	public String getColor() {
		return color;
	}

}
