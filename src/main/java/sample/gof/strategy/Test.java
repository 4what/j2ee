package sample.gof.strategy;

public class Test {


	public static void main(String[] args) {
		Calculator calculator = new Calculator(new Plus());
		calculator.execute();

		calculator.setOperation(new Minus());
		calculator.execute();
	}
}
