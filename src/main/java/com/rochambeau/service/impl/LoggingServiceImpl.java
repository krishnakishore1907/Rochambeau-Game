package com.rochambeau.service.impl;

import java.util.Objects;

import com.rochambeau.service.LoggingService;

/**
 * Created singleton Service class for logging. Singleton is not mandatory here,
 * but it is created as it suits for this application
 * 
 * @author Krishna Kishore
 * 
 */
public class LoggingServiceImpl implements LoggingService {

	private static LoggingServiceImpl loggingServiceImpl = null;

	private LoggingServiceImpl() {
	}

	public static LoggingServiceImpl getLoggingServiceImpl() {
		if (Objects.isNull(loggingServiceImpl)) {
			synchronized (LoggingServiceImpl.class) {
				if (Objects.isNull(loggingServiceImpl)) {
					loggingServiceImpl = new LoggingServiceImpl();
				}
			}
		}

		return loggingServiceImpl;
	}

	@Override
	public void log(String logging) {
		System.out.println(logging);
	}

	@Override
	public void blankLine() {
		System.out.println();
	}

	@Override
	public void logOnSameLine(String logging) {
		System.out.print(logging);
	}

}
