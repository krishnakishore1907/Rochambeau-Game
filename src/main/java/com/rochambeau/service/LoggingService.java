package com.rochambeau.service;

/**
 * Interface created for logging
 * 
 * @author Krishna Kishore
 */

public interface LoggingService {

	/**
	 * Logging the contents on console on new line
	 */
	void log(String logging);

	/**
	 * Logging for a new line
	 */
	void blankLine();

	/**
	 * Logging on the same line
	 */
	void logOnSameLine(String logging);

}
