/**
 *
 */
package com.rochambeau.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import com.rochambeau.service.InputService;
import com.rochambeau.service.StageService;
import com.rochambeau.service.impl.InputServiceImpl;

/**
 * @author Krishna Kishore
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

	private static Player player;

	@Mock
	InputServiceImpl inputServiceImpl;
	@Mock
	static InputService inputService;
	@Mock
	private static StageService stageService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		stageService = new StageService(0);
		player = new Player("Test");
	}

	@After
	public void cleanResources() {
		stageService = null;
		player = null;
	}

	/***
	 * 
	 * since we have set stage as 0, we have the same opponents with player as well
	 * as player is default set to stage 0.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testgetOpponentsFunction() throws Exception {
		assertEquals(StageService.getOpponents(0), player.getOpponents());
	}

	/***
	 * 
	 * Initially the player is set to level 0. It is a basic check we are doing
	 */
	@Test
	public void getCurrentLevel() {
		Assert.assertEquals(new Integer(0), player.getCurrentLevel());
	}

	/***
	 * 
	 * checking if player has been advanced to next level.
	 */
	@Test
	public void advanceToNextLevel_valid() {
		stageService = new StageService(1);
		player.advanceToNextLevel();
		Assert.assertEquals(new Integer(1), player.getCurrentLevel());
	}

	/***
	 * we are just validating the allowed player name here
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNameValidationWithOutSpace() throws Exception {
		assertTrue(Whitebox.invokeMethod(player, "nameValidation", "Gamer"));
	}

	@Test
	public void testNameValidationWithSpace() throws Exception {
		assertTrue(Whitebox.invokeMethod(player, "nameValidation", "Gamer Series"));
	}

	@Test
	public void testNameValidationWithInvalidName() throws Exception {
		assertFalse(Whitebox.invokeMethod(player, "nameValidation", "Gamer009"));
	}

	@Test
	public void testSavePlayer() {
		player.save();
		File file = new File(System.getProperty("user.home") + "/rochambeau/" + player.getName() + ".ser");
		assertTrue(file.exists());
		file.delete();
	}

}