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
				System.out.print(board[y][x] + " ");
			}
			System.out.println();
		}
	}

}
