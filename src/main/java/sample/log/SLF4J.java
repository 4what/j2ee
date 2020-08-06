package sample.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SLF4J {
	private static Logger logger = LoggerFactory.getLogger(SLF4J.class);


	public static void main(String[] args) {
		logger.trace("trace: {}", new Date());
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
}
