package com.rohm.mtg.utils.mkmcrawler.model;

public enum SellerCountry {

	AUSTRIA(1),
	BELGIUM(2),
	BULGARIA(3),
	CANADA(33),
	GERMANY(7);

	private int id;

	private SellerCountry(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}

}
