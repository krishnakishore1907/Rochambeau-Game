package utility;

import com.rochambeau.service.LoggingService;
import com.rochambeau.service.impl.LoggingServiceImpl;

/**
 * Utility class to have JVM hook and System exiting.
 * 
 * @author Krishna Kishore
 * 
 */
public class SystemExitUtility extends Thread {

	private static LoggingService LOGGER = LoggingServiceImpl.getLoggingServiceImpl();

	@Override
	public void run() {
		LOGGER.blankLine();
		LOGGER.log("Rochambeau application has been shutdown ...");
	}

	public static void exit() {
		System.exit(-1);
	}

	public static void exitWithMsg(String msg) {
		LOGGER.log(msg);
		System.exit(-1);
	}
}