
public class Player {

	private int color;
	private int x;
	private int y;
	private int score;
	private boolean active;

	public Player(int x, int y, int color) {
		this.color = color;
		this.color = color;
		this.x = x;
		this.y = y;
		this.score = 0;
		this.active = false;
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

	public int getScore() {
		return score;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void toggleActive() {
		if (this.active) {
			this.active = false;
		} else {
			this.active = true;
		}
	}

}
