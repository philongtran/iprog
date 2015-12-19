
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
					if (player1.getY() - 1 >= 0
							&& !(player1.getX() == player2.getX() && player1.getY() - 1 == player2.getY())) {
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player1.moveUp();
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						board.set(player1.getX(), player1.getY(), -1);
						togglePlayer();
					} else {
						break;
					}
				} else {
					if (player2.getY() - 1 >= 0
							&& !(player1.getX() == player2.getX() && player1.getY() == player2.getY() - 1)) {
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player2.moveUp();
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						board.set(player2.getX(), player2.getY(), -2);
						togglePlayer();
					} else {
						break;
					}
				}
				break;
			case "s":
				if (player1.getActive()) {
					if (player1.getY() + 1 < board.getSizeY()
							&& !(player1.getX() == player2.getX() && player1.getY() + 1 == player2.getY())) {
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player1.moveDown();
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						board.set(player1.getX(), player1.getY(), -1);
						togglePlayer();
					} else {
						break;
					}
				} else {
					if (player2.getY() + 1 < board.getSizeY()
							&& !(player1.getX() == player2.getX() && player1.getY() == player2.getY() + 1)) {
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player2.moveDown();
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						board.set(player2.getX(), player2.getY(), -2);
						togglePlayer();
					} else {
						break;
					}
				}
				break;
			case "a":
				if (player1.getActive()) {
					if (player1.getX() - 1 >= 0
							&& !(player1.getX() - 1 == player2.getX() && player1.getY() == player2.getY())) {
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player1.moveLeft();
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						board.set(player1.getX(), player1.getY(), -1);
						togglePlayer();
					} else {
						break;
					}
				} else {
					if (player2.getX() - 1 >= 0
							&& !(player1.getX() == player2.getX() - 1 && player1.getY() == player2.getY())) {
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player2.moveLeft();
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						board.set(player2.getX(), player2.getY(), -2);
						togglePlayer();
					} else {
						break;
					}
				}
				break;
			case "d":
				if (player1.getActive()) {
					if (player1.getX() + 1 < board.getSizeX()
							&& !(player1.getX() + 1 == player2.getX() && player1.getY() == player2.getY())) {
						oldPositionX = player1.getX();
						oldPositionY = player1.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player1.moveRight();
						player1.addScore(board.getValue(player1.getX(), player1.getY()));
						board.set(player1.getX(), player1.getY(), -1);
						togglePlayer();
					} else {
						break;
					}
				} else {
					if (player2.getX() + 1 < board.getSizeX()
							&& !(player1.getX() == player2.getX() + 1 && player1.getY() == player2.getY())) {
						oldPositionX = player2.getX();
						oldPositionY = player2.getY();
						board.set(oldPositionX, oldPositionY, 0);
						player2.moveRight();
						player2.addScore(board.getValue(player2.getX(), player2.getY()));
						board.set(player2.getX(), player2.getY(), -2);
						togglePlayer();
					} else {
						break;
					}
				}
				break;
			}
			System.out.print("Score B: " + player1.getScore() + " Score W: " + player2.getScore() + " | ");
			if (player1.getActive() && player1.getScore() < 100 && player2.getScore() < 100) {
				System.out.println("Black to move");
			} else if (player2.getActive() && player1.getScore() < 100 && player2.getScore() < 100) {
				System.out.println("White to move");
			} else {
				if (player1.getScore() >= 100) {
					System.out.println("Black wins");
				} else {
					System.out.println("White wins");
				}
			}
			board.show();
		}
	}

	private static void togglePlayer() {
		player1.toggleActive();
		player2.toggleActive();
	}

}
