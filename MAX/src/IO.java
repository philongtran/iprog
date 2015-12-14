
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This class provides support of user input/output handling
 * 
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 *
 */
public class IO {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Prints out a given input to System.out
	 * 
	 * @param s
	 *            - the input which should be printed
	 */
	public static void writeln(String s) {
		writeAndFlushLine(s);
	}

	/**
	 * Reads an input from a user by a given question
	 * 
	 * @param question
	 * @return user input
	 * @throws IOException
	 */
	public static String promptAndRead(String question) throws IOException {
		writeAndFlush(question);
		return reader.readLine();
	}

	private static void writeAndFlush(String s) {
		System.out.print(s);
		System.out.flush();
	}

	private static void writeAndFlushLine(String s) {
		System.out.println(s);
		System.out.flush();
	}

	private static String promptAndReadWithTrim(String question) throws IOException {
		return promptAndRead(question).trim();
	}

	public static int readInt(String s) throws Exception {
		return Integer.parseInt(promptAndReadWithTrim(s));
	}

	public static long readLong(String s) throws Exception {
		return Long.parseLong(promptAndReadWithTrim(s));
	}

	public static byte readByte(String s) throws Exception {
		return Byte.parseByte(promptAndReadWithTrim(s));
	}

	public static short readShort(String s) throws Exception {
		return Short.parseShort(promptAndReadWithTrim(s));
	}

	public static double readDouble(String question) throws Exception {
		return Double.parseDouble(promptAndReadWithTrim(question));
	}

	public static float readFloat(String s) throws Exception {
		return Float.parseFloat(promptAndReadWithTrim(s));
	}

	public static BigInteger readBigInt(String s) throws IOException {
		return new BigInteger(promptAndReadWithTrim(s));
	}

	public static BigDecimal readBigDecimal(String s) throws IOException {
		return new BigDecimal(promptAndReadWithTrim(s));
	}
}