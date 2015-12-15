
public class MAX {

	public static void main(String[] args) throws Exception {
		int oldPositionX;
		int oldPositionY;

		Player player1 = new Player(4, 4, -1);
		Player player2 = new Player(3, 3, -2);
		Board board = new Board(8, 8);
		board.set(player1.getX(), player1.getY(), player1.getColor());
		board.set(player2.getX(), player2.getY(), player2.getColor());
		System.out.println("Score P1: " + player1.getScore());
		board.show();

		while (player1.getScore() < 100 && player2.getScore() < 100) {
			String input = IO.promptAndRead("i: ");
			switch (input) {
			case "w":
				oldPositionX = player1.getX();
				oldPositionY = player1.getY();
				board.set(oldPositionX, oldPositionY, 0);
				player1.moveUp();
				player1.addScore(board.getValue(player1.getX(), player1.getY()));
				board.set(player1.getX(), player1.getY(), -1);
				break;
			case "s":
				oldPositionX = player1.getX();
				oldPositionY = player1.getY();
				board.set(oldPositionX, oldPositionY, 0);
				player1.moveDown();
				player1.addScore(board.getValue(player1.getX(), player1.getY()));
				board.set(player1.getX(), player1.getY(), -1);
				break;
			case "a":
				oldPositionX = player1.getX();
				oldPositionY = player1.getY();
				board.set(oldPositionX, oldPositionY, 0);
				player1.moveLeft();
				player1.addScore(board.getValue(player1.getX(), player1.getY()));
				board.set(player1.getX(), player1.getY(), -1);
				break;
			case "d":
				oldPositionX = player1.getX();
				oldPositionY = player1.getY();
				board.set(oldPositionX, oldPositionY, 0);
				player1.moveRight();
				player1.addScore(board.getValue(player1.getX(), player1.getY()));
				board.set(player1.getX(), player1.getY(), -1);
				break;
			}
			System.out.println("Score P1: " + player1.getScore());
			board.show();
		}
	}

}
