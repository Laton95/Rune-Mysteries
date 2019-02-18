package com.laton95.runemysteries.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLog {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static final String prefix = "RuneMysteries: ";
	
	public static void info(String message, Object... params) {
		logger.info(prefix + message, params, params);
	}
	
	public static void debug(String message, Object... params) {
		logger.debug(prefix + message, params);
	}
	
	public static void warn(String message, Object... params) {
		logger.warn(prefix + message, params);
	}
	
	public static void error(String message, Object... params) {
		logger.error(prefix + message, params);
	}
}
