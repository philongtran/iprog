public class Display {

	private Player[] player;
	private Board board;

	/**
	 * The constructor of the class.
	 * 
	 * @param player
	 *            - The array of players
	 * @param board
	 *            - The game board
	 */
	public Display(Player[] player, Board board) {
		this.player = player;
		this.board = board;
	}

	/**
	 * Displays the score board and the play board to the screen.
	 * 
	 * @param playerID
	 *            - The ID of the player
	 * @param checkScore
	 *            - Boolean if score limit is reached
	 */
	public void show(int playerID, boolean checkScore) {
		showScore(playerID, checkScore);
		showBoard(board);
	}

	/**
	 * Displays the score board to the screen.
	 * 
	 * @param playerID
	 *            - The ID of the player
	 * @param checkScore
	 *            - Boolean if score limit is reached
	 */
	public void showScore(int playerID, boolean checkScore) {
		String score = "";
		for (int i = 0; i < player.length; i++) {
			score += "Score " + returnLetter(player[i].getColor()) + ": " + player[i].getScore() + " ";
		}
		if (checkScore) {
			score += "| " + returnLetter(player[playerID].getColor()) + " wins";
		} else {
			if (playerID == player.length - 1) {
				score += "| " + returnLetter(player[0].getColor()) + " to move";
			} else {
				score += "| " + returnLetter(player[playerID + 1].getColor()) + " to move";
			}
		}
		IO.writeln(score);
		// showBoard(board);
	}

	/**
	 * Displays the formated board on the screen. It shows its values and spots
	 * the players and then assigns them with a predefined letter or generic
	 * name.
	 * 
	 * @param board
	 *            - The game board
	 */
	private void showBoard(Board board) {
		for (int y = 0; y < board.getSizeY(); y++) {
			for (int x = 0; x < board.getSizeX(); x++) {
				if (board.getValue(x, y) < 0) {
					IO.write(returnLetter(board.getValue(x, y)) + " ");
				} else {
					IO.write(board.getValue(x, y) + " ");
				}

			}
			IO.writeln("");
		}
	}

	/**
	 * Assigning letters from the colors to the players.
	 * 
	 * @param color
	 *            - Color of the player
	 * @return - Letter of the player if available, otherwise generic name
	 */
	private String returnLetter(int color) {
		switch (color) {
		case -1:
			return "B";
		case -2:
			return "W";
		case -3:
			return "G";
		case -4:
			return "L";
		default:
			return "P" + (color * (-1));
		}
	}

}
