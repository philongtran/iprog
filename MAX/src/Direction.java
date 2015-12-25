/**
 * Visible representation of a players direction
 *
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 */
enum Direction {

	LEFT("a"), RIGHT("d"), UP("w"), DOWN("s"), RESTART("r"), QUIT("q"), UNKNOWN("");

	private final String direction;

	/**
	 * Representation of direction as string.
	 * 
	 * @param directionAsString
	 *            - Representation of direction as string
	 */
	private Direction(String directionAsString) {
		this.direction = directionAsString;
	}

	/**
	 * Returns the direction as string.
	 * 
	 * @return - Returns the direction as string
	 */
	private String getDirection() {
		return direction;
	}

	/**
	 * Gets the corresponding direction value of a string.
	 * 
	 * @param direction
	 *            - w,a,s,d
	 * @return - Direction as Enum
	 */
	static Direction of(String direction) {
		for (Direction directionEnum : Direction.values()) {
			if (directionEnum.getDirection().equals(direction)) {
				return directionEnum;
			}
		}
		return UNKNOWN;
	}
}
