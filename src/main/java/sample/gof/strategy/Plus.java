package sample.gof.strategy;

public class Plus implements Operation {
	@Override
	public void execute() {
		System.out.println("Plus.execute()");
	}
}
