package com.rohm.mtg.utils.mkmcrawler.model;

public enum Condition {

	MINT(1),
	NEAR_MINT(2),
	EXCELLENT(3),
	GOOD(4),
	LIGHT_PLAYED(5),
	PLAYED(6),
	POOR(7);

	private int id;

	private Condition(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}


}
