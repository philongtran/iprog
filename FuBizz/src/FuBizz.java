
public class FuBizz {

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
