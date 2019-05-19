package com.rochambeau.service;

/**
 * Interface for reading/Loading the input from the user's console
 * 
 * @author Krishna Kishore
 * 
 */
public interface InputService {

	/**
	 * Method for initial setup reading from the config files
	 */
	void initialize();

	String next();

	String nextLine();

}
