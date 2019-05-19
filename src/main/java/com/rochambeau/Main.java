package com.rochambeau;

import com.rochambeau.service.Game;
import com.rochambeau.service.InputService;
import com.rochambeau.service.impl.GameService;
import com.rochambeau.service.impl.InputServiceImpl;

/***
 * 
 * Main class that triggers the game
 * 
 * @author Krishna Kishore
 */
public class Main {

	public static void main(String[] args) {
		final InputService inputService = InputServiceImpl.getInputServiceImpl();
		inputService.initialize();
		startGame();
	}

	private static Integer startGame() {
		final Game game = new GameService();
		while (true) {
			game.showMenu();
		}
	}
}