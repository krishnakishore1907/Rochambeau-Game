package com.rochambeau.model;

import java.io.Serializable;

/**
 * Model class for the opponent
 * 
 * @author Krishna Kishore
 * 
 */
public class Opponent implements Characters, Serializable {

	private static final long serialVersionUID = -4649979124990114741L;
	private String name;
	private int level;

	public Opponent(String name, int level) {
		super();
		this.name = name;
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "opponent [level=" + level + ", name=" + name + " ]";
	}

	@Override
	public void save() {
		// since we are not saving opponent level, not implementing this.

	}

}