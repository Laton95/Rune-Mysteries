package com.laton95.runemysteries.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Function;

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
	
	public static <T> void printList(String header, List<T> list, Function<T, String> function) {
		StringBuilder output = new StringBuilder(header + "\n");
		for(T listItem : list) {
			output.append(function.apply(listItem)).append("\n");
		}
		info(output.toString());
	}
}
