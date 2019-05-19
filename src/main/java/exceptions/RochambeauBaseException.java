/**
 *
 */
package exceptions;

import com.rochambeau.service.impl.LoggingServiceImpl;

import utility.SystemExitUtility;

/**
 * Parent class of Exceptions created to catch Exceptions across the program
 * 
 * @author Krishna Kishore
 * 
 */
public class RochambeauBaseException extends Exception {

	private static final long serialVersionUID = 2278153285060084694L;

	public RochambeauBaseException() {
		LoggingServiceImpl.getLoggingServiceImpl().log("There is some exception in rochambeauBaseException");
		SystemExitUtility.exit();
	}

}
