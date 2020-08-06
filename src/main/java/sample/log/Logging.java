package sample.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
	private static Logger logger = Logger.getLogger(Logging.class.getName());


	public static void main(String[] args) {
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);

		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);

		logger.addHandler(consoleHandler);

		logger.finest("finest");
		logger.finer("finer");
		logger.fine("fine");
		logger.config("config");
		logger.info("info");
		logger.warning("warning");
		logger.severe("severe");
	}
}
