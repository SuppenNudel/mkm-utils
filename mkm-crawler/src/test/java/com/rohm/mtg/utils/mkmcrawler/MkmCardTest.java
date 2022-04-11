package com.rohm.mtg.utils.mkmcrawler;

import org.junit.jupiter.api.Test;

import com.rohm.mtg.utils.mkmcrawler.model.Condition;
import com.rohm.mtg.utils.mkmcrawler.model.Language;
import com.rohm.mtg.utils.mkmcrawler.model.MkmCardInfo;
import com.rohm.mtg.utils.mkmcrawler.model.SellerCountry;

public class MkmCardTest {

	@Test
	public void withComma() {
		MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").get();
		System.out.println(mkmCardInfo);
	}

	@Test
	public void sellerCountry() {
		MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").sellerCountry(SellerCountry.GERMANY).get();
		System.out.println(mkmCardInfo);
	}

	@Test
	public void sellerCountries() {
		MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").sellerCountry(SellerCountry.GERMANY, SellerCountry.CANADA).get();
		System.out.println(mkmCardInfo);
	}

	@Test
	public void language() {
		MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").language(Language.EN).get();
		System.out.println(mkmCardInfo);
	}

	@Test
	public void languages() {
		MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").language(Language.EN, Language.DE).get();
		System.out.println(mkmCardInfo);
	}

	@Test
	public void condition() {
		MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").minCondition(Condition.EXCELLENT).get();
		System.out.println(mkmCardInfo);
	}

	@Test
	public void countryLanguageCondition() {
		MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").sellerCountry(SellerCountry.GERMANY).language(Language.EN).minCondition(Condition.EXCELLENT).get();
		System.out.println(mkmCardInfo);
	}

}
