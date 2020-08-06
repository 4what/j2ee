package sample.gof.chain_of_responsibility;

public class StderrLogger extends Logger {
	public StderrLogger(int level) {
		this.level = level;
	}

	@Override
	public void write(String msg) {
		System.out.println("StderrLogger: " + msg);
	}
}
