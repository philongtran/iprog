public class MathPlotter {

	private static double start = 0.0;
	private static double steps = 1.0;
	private static double range = 10.0;

	public static void main(String[] args) throws Exception {
		boolean run = true;
		while (run) {
			String select = IO.promptAndRead("Funktion: ").toLowerCase();
			if (select.equals("ende")) {
				return;
			}
			start = IO.readDouble("Start: ");
			range = IO.readDouble("Ende: ");
			steps = IO.readDouble("Schritte: ");

			switch (select) {
			case "exp":
				plot(new MathExp());
				break;
			case "sqrt":
				plot(new MathSqrt());
				break;
			case "sin":
				plot(new MathSin());
				break;
			case "cos":
				plot(new MathCos());
				break;
			case "log":
				plot(new MathLog());
				break;
			case "tan":
				plot(new MathTan());
				break;
			case "square":
				plot(new MathSquare());
				break;
			case "cube":
				plot(new MathCube());
				break;
			case "ende":
				run = false;
				return;
			default:
				break;
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
