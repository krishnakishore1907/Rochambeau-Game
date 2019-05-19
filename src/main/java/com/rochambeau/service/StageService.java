/**
 *
 */
package com.rochambeau.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.rochambeau.model.Actions;
import com.rochambeau.model.Opponent;
import com.rochambeau.model.Stage;

import utility.SystemExitUtility;

/**
 * Class handles Stage related activities
 * 
 * @author Krishna Kishore
 * 
 */
public class StageService {

	static {
		stageMap = new HashMap<>();
	}

	private static final Map<Integer, Stage> stageMap;

	public StageService(int level) {
		stageMap.put(level, new Stage());
	}

	public static void setStageList(Integer stage, Stage stageObj) {
		stageMap.put(stage, stageObj);
	}

	/**
	 * Returns the total number of opponents available in the whole game.
	 */
	public static Integer getTotalOpponentsCount() {
		return stageMap.entrySet().stream().map(p -> p.getValue().getOpponents().size()).reduce(0, (sum, p) -> sum + p);
	}

	public static List<Opponent> getOpponents(int stage) {
		return getStageDetailsFromMap(stage).getOpponents().stream().collect(Collectors.toList());
	}

	public void setOpponents(int stage, List<Opponent> opponents) {
		getStageDetailsFromMap(stage).setOpponents(opponents);
	}

	public static List<Actions> getActions(int stage) {
		return getStageDetailsFromMap(stage).getActions().stream().collect(Collectors.toList());
	}

	public void setActions(int stage, List<Actions> actions) {
		getStageDetailsFromMap(stage).setActions(actions);
	}

	public Optional<Opponent> getOpponentByName(Integer level, String name) {
		return getStageDetailsFromMap(level).getOpponents().stream().filter(e -> e.getName().equals(name)).findFirst();
	}

	public static void setPointsForStage(Integer stage, Integer[][] points) {
		getStageDetailsFromMap(stage).setPointsForStage(points);
	}

	public static Integer getPoints(Integer stage, Integer playerMove, Integer opponentMove) {
		return getStageDetailsFromMap(stage).getPoints(playerMove, opponentMove);
	}

	public static void setMoves(int stage, Integer[][] moves) {
		getStageDetailsFromMap(stage).setPointsForStage(moves);
	}

	public static Stage getStageDetailsFromMap(Integer stage) {

		if (stageMap.get(stage) == null)
			SystemExitUtility.exitWithMsg("Stage " + stage + " is not a valid stage");

		return stageMap.get(stage);
	}
}
