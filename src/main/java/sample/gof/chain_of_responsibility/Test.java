package sample.gof.chain_of_responsibility;

public class Test {


	public static void main(String[] args) {
		Logger logger = new StdoutLogger(Logger.DEBUG).setNext(new StderrLogger(Logger.ERROR));

		logger.message("debug", Logger.DEBUG);
		logger.message("error", Logger.ERROR);
	}
}
