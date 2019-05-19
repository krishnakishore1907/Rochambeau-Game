/**
 *
 */
package com.rochambeau.service;

import java.io.IOException;

/**
 * Interface to have the key methods of the game
 * 
 * @author Krishna Kishore
 * 
 */
public interface Game {

	/**
	 * Method to display menu
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	void showMenu();

	/**
	 * Method to create an player
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	void createplayer();

	/**
	 * Method to explore about the game
	 */
	void explore();

	/**
	 * Method to start fighting
	 */
	void fight();

	/**
	 * Method to save the state of the player
	 */
	void save();

	/**
	 * Method to quit the game
	 */
	void quitGame();
}
