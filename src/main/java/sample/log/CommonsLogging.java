package sample.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonsLogging {
	private static Log log = LogFactory.getLog(CommonsLogging.class);


	public static void main(String[] args) {
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		log.fatal("fatal");
	}
}
