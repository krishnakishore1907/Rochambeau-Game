package exceptions;

import com.rochambeau.service.impl.LoggingServiceImpl;

import utility.SystemExitUtility;

public class InputNotFoundException extends Exception {

	public InputNotFoundException(String error) {
		LoggingServiceImpl.getLoggingServiceImpl().log(error);
		SystemExitUtility.exit();
	}

}
