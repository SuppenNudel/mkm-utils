package com.rohm.mtg.utils.mkmcrawler;

import com.rohm.mtg.utils.mkmcrawler.endpoints.MkmCardsEndpoint;
import com.rohm.mtg.utils.mkmcrawler.endpoints.MkmPreConEndpoint;
import com.rohm.mtg.utils.mkmcrawler.endpoints.MkmSearchEndpoint;

public abstract class MkmCrawler {

	/**
	 * @param cardName The exact card name to search for, case insenstive.
	 */
	public static MkmCardsEndpoint card(String cardName) {
		return new MkmCardsEndpoint(cardName);
	}
	public static MkmPreConEndpoint precon(String deckName) {
		return new MkmPreConEndpoint(deckName);
	}
	public static MkmSearchEndpoint search(String query) {
		return new MkmSearchEndpoint(query);
	}

}
