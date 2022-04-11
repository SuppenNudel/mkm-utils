package com.rohm.mtg.utils.mkmcrawler.model;

public enum Language {

	EN(1),
	DE(3);

	private int id;

	private Language(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}

}
