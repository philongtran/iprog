
public class MAX {

	private static Player player1;
	private static Player player2;
	private static Board board;

	public static void main(String[] args) throws Exception {
		int oldPositionX;
		int oldPositionY;

		player1 = new Player(4, 4, -1);
		player1.setActive(true);
		player2 = new Player(3, 3, -2);
		player2.setActive(false);
		board = new Board(8, 8);
		board.set(player1.getX(), player1.getY(), player1.getColor());
		board.set(player2.getX(), player2.getY(), player2.getColor());
		System.out.print("Score B: " + player1.getScore() + " Score W: " + player2.getScore() + " | ");
		System.out.println("Black to move");
		board.show();

		while (player1.getScore() < 100 && player2.getScore() < 100) {
			String input = IO.promptAndRead("i: ");
			switch (input) {
			case "w":
				if (player1.getActive()) {
					oldPositionX = player1.getX();
					oldPositionY = player1.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player1.moveUp();
					player1.addScore(board.getValue(player1.getX(), player1.getY()));
					board.set(player1.getX(), player1.getY(), -1);
					togglePlayer();
				} else {
					oldPositionX = player2.getX();
					oldPositionY = player2.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player2.moveUp();
					player2.addScore(board.getValue(player2.getX(), player2.getY()));
					board.set(player2.getX(), player2.getY(), -2);
					togglePlayer();
				}
				break;
			case "s":
				if (player1.getActive()) {
					oldPositionX = player1.getX();
					oldPositionY = player1.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player1.moveDown();
					player1.addScore(board.getValue(player1.getX(), player1.getY()));
					board.set(player1.getX(), player1.getY(), -1);
					togglePlayer();
				} else {
					oldPositionX = player2.getX();
					oldPositionY = player2.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player2.moveDown();
					player2.addScore(board.getValue(player2.getX(), player2.getY()));
					board.set(player2.getX(), player2.getY(), -2);
					togglePlayer();
				}
				break;
			case "a":
				if (player1.getActive()) {
					oldPositionX = player1.getX();
					oldPositionY = player1.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player1.moveLeft();
					player1.addScore(board.getValue(player1.getX(), player1.getY()));
					board.set(player1.getX(), player1.getY(), -1);
					togglePlayer();
				} else {
					oldPositionX = player2.getX();
					oldPositionY = player2.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player2.moveLeft();
					player2.addScore(board.getValue(player2.getX(), player2.getY()));
					board.set(player2.getX(), player2.getY(), -2);
					togglePlayer();
				}
				break;
			case "d":
				if (player1.getActive()) {
					oldPositionX = player1.getX();
					oldPositionY = player1.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player1.moveRight();
					player1.addScore(board.getValue(player1.getX(), player1.getY()));
					board.set(player1.getX(), player1.getY(), -1);
					togglePlayer();
				} else {
					oldPositionX = player2.getX();
					oldPositionY = player2.getY();
					board.set(oldPositionX, oldPositionY, 0);
					player2.moveRight();
					player2.addScore(board.getValue(player2.getX(), player2.getY()));
					board.set(player2.getX(), player2.getY(), -2);
					togglePlayer();
				}
				break;
			}
			System.out.print("Score B: " + player1.getScore() + " Score W: " + player2.getScore() + " | ");
			if (player1.getActive()) {
				System.out.println("Black to move");
			} else {
				System.out.println("White to move");
			}
			board.show();
		}
	}

	private static void togglePlayer() {
		player1.toggleActive();
		player2.toggleActive();
	}

}
