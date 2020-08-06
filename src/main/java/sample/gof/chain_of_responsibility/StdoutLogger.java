package sample.gof.chain_of_responsibility;

public class StdoutLogger extends Logger {
	public StdoutLogger(int level) {
		this.level = level;
	}

	@Override
	public void write(String msg) {
		System.out.println("StdoutLogger: " + msg);
	}
}
