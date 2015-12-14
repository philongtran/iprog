import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

public class MathPlotter {

	private static double start = 0.0;
	private static double steps = 1.0;
	private static double range = 10.0;

	public static void main(String[] args) throws Exception {
		boolean run = true;
		while (run) {
			String select = IO.promptAndRead("Funktion: ").toLowerCase();
			if (select.equals("ende")) {
				run = false;
			}

			if (run) {
				start = IO.readDouble("Start: ");
				range = IO.readDouble("Ende: ");
				steps = IO.readDouble("Schritte: ");

				switch (select) {
				case "exp":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return exp(value);
						}
					});
					break;
				case "sqrt":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return sqrt(value);
						}
					});
					break;
				case "sin":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return sin(value);
						}
					});
					break;
				case "cos":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return cos(value);
						}
					});
					break;
				case "log":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return log(value);
						}
					});
					break;
				case "tan":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return tan(value);
						}
					});
					break;
				case "square":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return pow(value, 2);
						}
					});
					break;
				case "cube":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return pow(value, 3);
						}
					});
					break;
				case "bisquare":
					plot(new D2Method() {

						@Override
						public double compute(double value) {
							return pow(value, 4);
						}
					});
					break;
				default:
					IO.writeln(select + " not found");
					break;
				}
			}
		}
	}

	public static void plot(D2Method meth) {
		System.out.println("Plot " + meth);
		for (double x = start; x <= range; x += steps) {
			IO.writeln(" " + x + " -> " + meth.compute(x));
		}
	}

}