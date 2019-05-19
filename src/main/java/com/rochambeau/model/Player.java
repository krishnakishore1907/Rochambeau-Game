package com.rochambeau.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.rochambeau.service.InputService;
import com.rochambeau.service.LoggingService;
import com.rochambeau.service.StageService;
import com.rochambeau.service.impl.InputServiceImpl;
import com.rochambeau.service.impl.LoggingServiceImpl;

import utility.ReadWriteUtil;

/**
 * Player class is the state of the "Character" designed to play
 * 
 * @author Krishna Kishore
 * 
 */
public class Player implements Characters, Serializable {

	private static final long serialVersionUID = 1821222719462678014L;
	private static final LoggingService LOGGER = LoggingServiceImpl.getLoggingServiceImpl();
	private static final InputService inputService = InputServiceImpl.getInputServiceImpl();

	private final String name;
	private Integer currentLevel;
	private Integer matchesPlayed;
	private Integer score;
	private List<Opponent> opponents;
	private List<Actions> actions;
	private Stage stage;

	public Player(String name) {
		this.matchesPlayed = 0;
		this.score = 50;
		this.name = name;
		this.currentLevel = 0;
		this.stage = StageService.getStageDetailsFromMap(0);
		this.opponents = Objects.requireNonNull(this.stage).getOpponents();
		this.actions = Objects.requireNonNull(this.stage).getActions();
	}

	public static Player setupPlayer() {
		boolean nameValidated = false;
		String playerName = null;

		while (!nameValidated) {
			LOGGER.log("Enter name for your character : ");
			playerName = inputService.next();
			nameValidated = nameValidation(playerName);
			if (!nameValidated) {
				LOGGER.log("Player name can only contain Characters and Space.");
				LOGGER.log("Please re-enter the name");
			}
		}

		Player existingPlayer = ReadWriteUtil.readObject(playerName);
		if (Objects.nonNull(existingPlayer)) {
			return existingPlayer;
		} else {
			return new Player(playerName);
		}
	}

	@Override
	public String toString() {
		return "Player [Name=" + name.toUpperCase() + ", CurrentStage=" + currentLevel + ", LevelsPlayed="
				+ matchesPlayed + ", SCORE =" + score + "]";
	}

	/**
	 * Method to get the opponents for the current Object
	 * 
	 * @return List of opponents for the Player
	 */
	public List<Opponent> getOpponents() {
		return opponents;
	}

	/**
	 * Method to get level of the object
	 * 
	 * @return level of the player
	 */

	public Integer getCurrentLevel() {
		return currentLevel;
	}

	public Optional<Actions> getActionByName(String name) {
		return actions.stream().filter(action -> action.getName().equalsIgnoreCase(name)).findFirst();
	}

	public String getActionNamebyPosition(int position) {
		return this.actions.get(position).getName();
	}

	public Integer getActionPosition(String name) {
		Optional<Actions> actionName = getActionByName(name);
		if (actionName.isPresent()) {
			return stage.getActions().indexOf(actionName.get());
		}
		return -1;
	}

	public void setCurrentLevel(Integer level) {
		currentLevel = level;
	}

	public void advanceToNextLevel() {
		try {
			currentLevel = currentLevel + 1;
			setOpponents(StageService.getOpponents(currentLevel));
			setActions(StageService.getActions(currentLevel));
		} catch (Exception e) {
			LOGGER.log("Stage" + currentLevel + " does not exits");
		}
	}

	public void setOpponents(List<Opponent> opponents) {
		this.opponents = opponents;
	}

	public List<Actions> getActions() {
		return this.actions;
	}

	public List<String> getActionNames() {
		return actions.stream().map(p -> p.getName()).collect(Collectors.toList());
	}

	public void setActions(List<Actions> actions) {
		this.actions = actions;
	}

	public String getName() {
		return name;
	}

	public Integer getScore() {
		return score;
	}

	public boolean isDead() {
		return score <= 0;
	}

	public Integer getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(Integer matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Opponent destroyOpponent() {
		if (Objects.nonNull(this.opponents)) {
			return this.opponents.remove(0);
		}
		return null;
	}

	public String getCurrentOpponent() {
		return this.opponents.get(0).getName();
	}

	@Override
	public void save() {
		ReadWriteUtil.writeObject(this);
	}

	/***
	 * since we can't have duplicate player name in this game, i am taking player
	 * name hashcode.
	 * 
	 */

	@Override
	public int hashCode() {
		return ((name == null) ? 0 : name.hashCode());
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		Player player = (Player) obj;
		if (this.getName().equals(player.getName()) && this.getCurrentLevel().equals(player.getCurrentLevel())
				&& this.getMatchesPlayed().equals(player.getMatchesPlayed())
				&& this.getScore().equals(player.getScore()))
			return true;
		return false;
	}

	private static boolean nameValidation(String playerName) {
		String regx = "[[a-zA-Z]+[\\s]*]+";
		return Pattern.matches(regx, playerName);
	}
}