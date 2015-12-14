
public class MAX {

	public static void main(String[] args) {
		Player player1 = new Player(4, 4, -1);
		Player player2 = new Player(3, 3, -2);
		Board board = new Board(8, 8);
		board.set(player1.getX(), player1.getY(), player1.getColor());
		board.set(player2.getX(), player2.getY(), player2.getColor());
		board.show();
	}

}
