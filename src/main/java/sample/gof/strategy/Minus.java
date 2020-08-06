package sample.gof.strategy;

public class Minus implements Operation {
	@Override
	public void execute() {
		System.out.println("Minus.execute()");
	}
}
