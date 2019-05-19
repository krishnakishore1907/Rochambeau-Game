package com.rochambeau.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for Stage 0 of the Game
 * 
 * @author Krishna Kishore
 * 
 */
public class Stage implements Serializable {

	private static final long serialVersionUID = -7837099603885039435L;
	private List<Opponent> opponents = new ArrayList<>();
	private List<Actions> actions = new ArrayList<>();
	private Integer[][] points;

	public void setOpponents(List<Opponent> opponents) {
		this.opponents = opponents;
	}

	public void setActions(List<Actions> actions) {
		this.actions = actions;
	}

	public List<Opponent> getOpponents() {
		List<Opponent> result = new ArrayList<>();
		result.addAll(opponents);
		return result;
	}

	public List<Actions> getActions() {
		List<Actions> result = new ArrayList<>();
		result.addAll(actions);
		return result;
	}

	public void setPointsForStage(Integer[][] points) {
		this.points = new Integer[points.length][points[0].length];

		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[0].length; j++) {
				this.points[i][j] = points[i][j];
			}
		}
	}

	public Integer getPoints(Integer playerMove, Integer opponentMove) {
		return this.points[playerMove][opponentMove];
	}
}