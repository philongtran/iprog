
public class PlayerPosition {

	private int[][] position;

	public PlayerPosition(int playerCount, int boardDimensionX, int boardDimensionY) {
		switch (playerCount) {
		case 2:
			int p1x = boardDimensionX / 2;
			int p1y = boardDimensionY / 2;
			int p2x = boardDimensionX / 2 - 1;
			int p2y = boardDimensionY / 2 - 1;
			position = new int[][] { { p1x, p1y }, { p2x, p2y } };
			break;
		default:
			IO.writeln("error in creating player positions");
			break;
		}
	}

	public int[][] getPosition() {
		return position;
	}

}
