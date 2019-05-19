/**
 *
 */
package com.rochambeau.service.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import com.rochambeau.model.Player;
import com.rochambeau.service.StageService;

/**
 * @author Krishna Kishore
 * 
 */

public class RochambeauTest {

	private GameService game;
	private Player player;
	private StageService stageService;

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Before
	public void init() {
		game = new GameService();
		stageService = new StageService(0);
		player = new Player("Test");
	}

	@After
	public void CleanResources() throws Exception {
		game = null;
		player = null;
		stageService = null;
	}

	@Test
	public void save() {
		game.setplayer(player);
		game.save();
		File file = new File(System.getProperty("user.home") + "/rochambeau/" + player.getName() + ".ser");
		assertTrue(file.exists());
	}

}