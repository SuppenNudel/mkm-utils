package com.rohm.mtg.utils.mkmcrawler.model;

public class MkmDeckInfo {

	private String deckName;
	private int availableItems;
	private String from;
	private double trend;

	public MkmDeckInfo(String deckName) {
		this.deckName = deckName;
	}

	@Override
	public String toString() {
		return String.format("%s %.02f€ - %.02f€ ^", deckName, from, trend);
	}

	public String getDeckName() {
		return deckName;
	}
	public int getAvailableItems() {
		return availableItems;
	}
	public String getFrom() {
		return from;
	}
	public double getTrend() {
		return trend;
	}
	public void setAvailableItems(int availableItems) {
		this.availableItems = availableItems;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTrend(double trend) {
		this.trend = trend;
	}

}
