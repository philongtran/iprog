/**
 * Solves the FuBizz Challenge
 * 
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 */
public class FuBizz {

	/**
	 * Replaces multiple of 3 with "Fizz" <br>
	 * Replaces multiple of 5 with "Buzz" <br>
	 * Replaces multiple of 3 and 5 with "FuBizz" <br>
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int n = IO.readInt("n: ");

		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				IO.write("FuBizz, ");
			} else if (i % 3 == 0 && i % 5 != 0) {
				IO.write("Fizz, ");
			} else if (i % 5 == 0 && i % 3 != 0) {
				IO.write("Buzz, ");
			} else {
				IO.write(i + ", ");
			}
		}
	}

}
