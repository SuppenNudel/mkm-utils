package com.rohm.mtg.utils.mkmcrawler;

import org.junit.jupiter.api.Test;

import com.rohm.mtg.utils.mkmcrawler.model.Language;
import com.rohm.mtg.utils.mkmcrawler.model.MkmDeckInfo;
import com.rohm.mtg.utils.mkmcrawler.model.SellerCountry;

public class MkmPreConTest {

	@Test
	public void countryLanguageCondition() {
		MkmDeckInfo mkmDeckInfo = MkmCrawler.precon("Challenger-Decks-2021-Dimir-Rogue")
				.sellerCountry(SellerCountry.GERMANY).language(Language.EN).get();
		System.out.println(mkmDeckInfo);
	}

}
