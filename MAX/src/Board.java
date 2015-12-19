import java.util.Random;

public class Board {
	private final int sizeX;
	private final int sizeY;
	private int[][] board;
	private Random random;

	public Board(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.board = new int[sizeY][sizeX];
		this.random = new Random();
		this.initialize();
	}

	public void initialize() {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				board[y][x] = random.nextInt(9) + 1;
			}
		}
	}

	public void show() {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				if (board[y][x] == -1) {
					System.out.print("b ");
				} else if (board[y][x] == -2) {
					System.out.print("w ");
				} else {
					System.out.print(board[y][x] + " ");
				}

			}
			System.out.println();
		}
	}

	public void set(int x, int y, int value) {
		this.board[y][x] = value;
	}

	public int getValue(int x, int y) {
		return this.board[y][x];
	}

	public int getSizeX() {
		return this.sizeX;
	}

	public int getSizeY() {
		return this.sizeY;
	}

}
