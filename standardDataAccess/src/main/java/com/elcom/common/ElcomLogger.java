package com.elcom.common;

import org.apache.log4j.Logger;

public final class ElcomLogger {

	private ElcomLogger() {
	}
	
	private static Logger logger = null;

	public static void info(Object object, String message)
	{
		logger = Logger.getLogger(object.getClass());
		logger.info(message);
	}

	public static void warn(Object object, String message)
	{
		logger = Logger.getLogger(object.getClass());
		logger.warn(message);
	}

	public static void error(Object object, String message)
	{
		logger = Logger.getLogger(object.getClass());
		logger.error(message);
	}

	public static void debug(Object object, String message)
	{
		logger = Logger.getLogger(object.getClass());
		logger.debug(message);
	}
}
