package com.rochambeau.model;

import java.io.Serializable;

/**
 * Model class for player Actions
 * 
 * @author Krishna Kishore
 * 
 */
public class Actions implements Serializable {

	private static final long serialVersionUID = 3469120023546049882L;
	private String name;
	private Integer damage;

	public Actions(String name, Integer damage) {
		this.name = name;
		this.damage = damage;
	}

	@Override
	public String toString() {
		return "Actions [name=" + name + "]";
	}

	/***
	 * since we can't have duplicate action name in this game, i am taking action
	 * name for hashcode.
	 * 
	 */
	@Override
	public int hashCode() {
		return ((name == null) ? 0 : name.hashCode()) + ((damage == null) ? 0 : damage.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Actions action = (Actions) obj;
		if (this.getName().equals(action.getName()) && this.getDamage().equals(action.getDamage()))
			return true;
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDamage() {
		return damage;
	}

	public void setDamage(Integer damage) {
		this.damage = damage;
	}

}
