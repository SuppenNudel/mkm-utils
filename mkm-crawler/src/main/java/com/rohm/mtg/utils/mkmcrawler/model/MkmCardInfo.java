package com.rohm.mtg.utils.mkmcrawler.model;

public class MkmCardInfo {

	private String cardName;
	private int availableItems;
	private int versions;
	private double from;
	private double trend;

	public MkmCardInfo(String cardName) {
		this.cardName = cardName;
	}

	@Override
	public String toString() {
		return String.format("%s %.02f€ - %.02f€ ^", cardName, from, trend);
	}

	public String getCardName() {
		return cardName;
	}
	public int getAvailableItems() {
		return availableItems;
	}
	public double getFrom() {
		return from;
	}
	public double getTrend() {
		return trend;
	}
	public int getVersions() {
		return versions;
	}

	public void setAvailableItems(int availableItems) {
		this.availableItems = availableItems;
	}

	public void setVersions(int versions) {
		this.versions = versions;
	}

	public void setFrom(double from) {
		this.from = from;
	}

	public void setTrend(double trend) {
		this.trend = trend;
	}

}
