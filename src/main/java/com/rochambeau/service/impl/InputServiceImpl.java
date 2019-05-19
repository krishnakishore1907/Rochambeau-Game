package com.rochambeau.service.impl;

import java.util.Objects;
import java.util.Scanner;

import com.rochambeau.service.InputService;
import com.rochambeau.service.StageService;

import util.parsers.GameLevelParser;
import util.parsers.ParseUtil;
import utility.SystemExitUtility;

/**
 * Singleton class created for reading input from the console
 * 
 * @author Krishna Kishore
 * 
 */
public class InputServiceImpl implements InputService {

	private static InputServiceImpl inputServiceImpl = null;
	private static final String CONFIG_DIRECTORY = "config/";
	private static final String CARTOON_DIRECTORY = "cartoons/";
	private final Scanner scanner;

	private InputServiceImpl() {
		this.scanner = new Scanner(System.in);
	}

	/**
	 * static method returning singleton instance. For thread safety, double
	 * checking and synchronized is taken.
	 * 
	 * @return InputServiceImpl
	 */
	public static InputServiceImpl getInputServiceImpl() {
		if (Objects.isNull(inputServiceImpl)) {
			synchronized (InputServiceImpl.class) {
				if (Objects.isNull(inputServiceImpl)) {
					inputServiceImpl = new InputServiceImpl();
				}
			}
		}

		return inputServiceImpl;
	}

	/**
	 * Method for initial setup reading from the config files
	 */
	@Override
	public void initialize() {

		// Registering a shutdown hook for JVM Termination activity
		Runtime.getRuntime().addShutdownHook(new SystemExitUtility());

		// loading input game level details file
		new GameLevelParser().readData(CONFIG_DIRECTORY + "Game_Levels.txt");
		// Parsing Points for various stages
		ParseUtil parseUtil = new ParseUtil();
		try {

			StageService.setMoves(0, parseUtil.ReadFileIntoMatrix(CONFIG_DIRECTORY + "Level_1_Points.txt", 3, 3));
			StageService.setMoves(1, parseUtil.ReadFileIntoMatrix(CONFIG_DIRECTORY + "Level_2_Points.txt", 4, 4));
			StageService.setMoves(2, parseUtil.ReadFileIntoMatrix(CONFIG_DIRECTORY + "Level_3_Points.txt", 7, 7));
			/*
			 * PresentationUtil
			 * .printSlowMotionImage(parseUtil.ReadFileIntoMatrix(CARTOON_DIRECTORY +
			 * "welcome.txt", 3, 3));
			 */
		} catch (NullPointerException e) {
			SystemExitUtility.exitWithMsg("Stage provided was not present in input_levels file");
		}

	}

	@Override
	public String next() {
		return scanner.next();
	}

	@Override
	public String nextLine() {
		return scanner.nextLine();
	}

}