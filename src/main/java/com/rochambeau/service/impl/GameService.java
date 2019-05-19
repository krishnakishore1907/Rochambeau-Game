package com.rochambeau.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.rochambeau.model.Actions;
import com.rochambeau.model.Opponent;
import com.rochambeau.model.Player;
import com.rochambeau.service.Game;
import com.rochambeau.service.InputService;
import com.rochambeau.service.LoggingService;
import com.rochambeau.service.StageService;

import utility.PresentationUtil;
import utility.SystemExitUtility;

/**
 * Class contains implementation logic of the Rochambeau game
 * 
 * @author Krishna Kishore
 * 
 */
public class GameService implements Game {

	private final InputService inputService = InputServiceImpl.getInputServiceImpl();
	private static final LoggingService LOGGER = LoggingServiceImpl.getLoggingServiceImpl();
	private static final Integer TOTAL_FIGHT_MOVES = 3;
	private Integer totalNoOfOpponents = 0;

	private Player player = null;

	public GameService() {
		totalNoOfOpponents = StageService.getTotalOpponentsCount();
	}

	public void setplayer(Player player) {
		this.player = player;
	}

	@Override
	public void showMenu() {
		LOGGER.blankLine();
		LOGGER.log("GAME MENU");
		LOGGER.log("1. Build/Create a Player");
		LOGGER.log("2. Explore ");
		LOGGER.log("3. Fight");
		LOGGER.log("4. Save");
		LOGGER.log("5. Quit");
		LOGGER.blankLine();

		String input = inputService.next();

		switch (input) {
		case "1":
			createplayer();
			break;
		case "2":
			explore();
			break;
		case "3":
			fight();
			break;
		case "4":
			save();
			break;
		case "5":
			quitGame();
			break;
		default:
			LOGGER.log(" Invalid input...! Please try again ");
		}
	}

	@Override
	public void quitGame() {
		if (Objects.nonNull(this.player)) {
			LOGGER.log("Press Y to save the current player");
			String select = inputService.next();
			if (select.equalsIgnoreCase("Y")) {
				this.player.save();
			}
		}
		SystemExitUtility.exit();
	}

	@Override
	public void save() {
		if (Objects.nonNull(this.player)) {
			this.player.save();
		} else {
			LOGGER.log("Null player cant be saved");
		}
	}

	private void checkIfplayerCreated() throws InterruptedException, IOException {
		if (Objects.isNull(player)) {
			LOGGER.log("player needs to be created before performing the Action");
			createplayer();
		}
	}

	@Override
	public void createplayer() {

		this.player = Player.setupPlayer();
		String card = "          Player " + this.player.getName().toUpperCase() + " is ready";
		PresentationUtil.printSlowMotionText(card);

	}

	@Override
	public void fight() {
		try {
			checkIfplayerCreated();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (this.player.getMatchesPlayed().equals(this.totalNoOfOpponents)) {
			LOGGER.log("You are already our SuperHero");
			return;
		}
		LOGGER.blankLine();

		LOGGER.logOnSameLine("          Your Opponent ");
		PresentationUtil.printSlowMotionText(" :: " + this.player.getCurrentOpponent().toUpperCase());
		LOGGER.blankLine();
		validateGame();
		concludeGame();
	}

	private void concludeGame() {
		Opponent destory = this.player.destroyOpponent();
		if (Objects.nonNull(destory)) {
			LOGGER.log("You have killed your opponent " + destory.getName());
		}

		Integer currentExp = this.player.getMatchesPlayed();
		this.player.setMatchesPlayed(++currentExp);

		if (currentExp.equals(this.totalNoOfOpponents)) {
			LOGGER.blankLine();
			LOGGER.log("************ YOU ARE THE WINNER ************");
			LOGGER.blankLine();
			LOGGER.log("Congratulations SuperHero");
			return;
		}

		if (this.player.getOpponents().isEmpty()) {
			LOGGER.blankLine();
			Integer nextStage = this.player.getCurrentLevel() + 1;
			LOGGER.log("Congratulations! You are promoted to Stage " + nextStage);
			this.player.advanceToNextLevel();
		}
	}

	private void calculateScore(Integer index) {
		// Using Random value to calculate move of computer
		Random random = new Random();
		Integer listedMoveSize = this.player.getActions().size();
		Integer opponentMove = random.nextInt(listedMoveSize);
		Integer moveScore = 0;
		try {
			moveScore = StageService.getPoints(this.player.getCurrentLevel(), index, opponentMove);
			this.player.setScore(this.player.getScore() + moveScore);
		} catch (Exception e) {
			SystemExitUtility
					.exitWithMsg("Invalid stage" + this.player.getCurrentLevel() + "selected while fetching score");
		}

		String currentOpponent = this.player.getCurrentOpponent();
		String currentOpponentMove = currentOpponent + " chose " + this.player.getActionNamebyPosition(opponentMove);

		if (this.player.isDead()) {
			LOGGER.blankLine();
			SystemExitUtility.exitWithMsg("ohhh ..! You are KILLED. ");
			PresentationUtil.printSlowMotionText("BETTER LUCK NEXT TIME ... ");
		}
		if (moveScore < 0) {
			LOGGER.log("You lost " + moveScore + " points because your opponent " + currentOpponentMove
					+ ".  YOUR SCORE : " + this.player.getScore());
		} else if (moveScore == 0) {
			LOGGER.log("Seems like you and " + currentOpponent + " chose same weapon. So Zero points!. YOUR SCORE :  "
					+ this.player.getScore());
		} else {
			LOGGER.log("You gained " + moveScore + " points because your opponent " + currentOpponentMove
					+ ".  YOUR SCORE : " + this.player.getScore());
		}
	}

	private void validateGame() {
		Player currentplayer = this.player;

		List<Actions> moves = currentplayer.getActions();
		LOGGER.blankLine();
		LOGGER.log("You have " + TOTAL_FIGHT_MOVES + " fights in this level");
		int index = 0;
		for (int i = 0; i < moves.size(); i++) {
			LOGGER.logOnSameLine(index++ + "=>" + moves.get(i).getName() + "  ");
		}

		LOGGER.blankLine();
		int movesSelected = 0;
		while (movesSelected < TOTAL_FIGHT_MOVES) {
			LOGGER.log("Hit with your number(Weapon) to proceed with the fight : ".toUpperCase());
			String input = inputService.next();
			Integer currentMove = 0;
			Integer allowedMoves = this.player.getActions().size();

			try {
				currentMove = Integer.parseInt(input);
				if (currentMove >= 0 && currentMove < allowedMoves) {
					calculateScore(currentMove);
					movesSelected++;
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				LOGGER.log("Invalid Move. Please select again");
			}
		}
	}

	@Override
	public void explore() {
		try {
			checkIfplayerCreated();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LOGGER.log(this.player.toString());
		LOGGER.blankLine();

		if (this.player.getMatchesPlayed().equals(this.totalNoOfOpponents)) {
			LOGGER.log("You are already a SuperHero");
			showMenu();
			return;
		}

		LOGGER.logOnSameLine("Your Weapons are : ");
		this.player.getActionNames().forEach(move -> LOGGER.logOnSameLine("=>" + move + "  "));

		LOGGER.blankLine();
		LOGGER.logOnSameLine("Your Opponents are : ");
		this.player.getOpponents().forEach(e -> LOGGER.logOnSameLine("=>" + e.getName() + "  "));

		LOGGER.blankLine();
	}
}