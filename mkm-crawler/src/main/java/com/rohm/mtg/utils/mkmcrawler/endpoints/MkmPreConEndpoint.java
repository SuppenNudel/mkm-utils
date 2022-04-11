package com.rohm.mtg.utils.mkmcrawler.endpoints;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.rohm.mtg.utils.mkmcrawler.model.Language;
import com.rohm.mtg.utils.mkmcrawler.model.MkmDeckInfo;
import com.rohm.mtg.utils.mkmcrawler.model.SellerCountry;


public class MkmPreConEndpoint extends AbstractMkmEndpoint<MkmDeckInfo> {

	private String deck;

	public MkmPreConEndpoint(String deck) {
		super("Magic/Products/Preconstructed-Decks/"+deck, MkmDeckInfo.class);
		this.deck = deck;
	}

	public MkmPreConEndpoint sellerCountry(SellerCountry... sellerCountries) {
		String key = "sellerCountry";
		resetQueryParam(key);
		Object[] sellerCountryIdsArray = Arrays.asList(sellerCountries).stream().map(SellerCountry::id).collect(Collectors.toList()).toArray();
		target = target.queryParam(key, sellerCountryIdsArray);
		return this;
	}

	public MkmPreConEndpoint language(Language... languages) {
		String key = "language";
		resetQueryParam(key);
		Object[] languageIdsArray = Arrays.asList(languages).stream().map(Language::id).collect(Collectors.toList()).toArray();
		target = target.queryParam(key, languageIdsArray);
		return this;
	}

	@Override
	protected MkmDeckInfo parseReponse(Response response) {
		String html = response.readEntity(String.class);
		MkmDeckInfo mkmCardInfo = parseDocumentForMkmDeckInfo(html);
		return mkmCardInfo;
	}

	private MkmDeckInfo parseDocumentForMkmDeckInfo(String html) {
		Document document = Jsoup.parse(html);
		Elements elements = document.select(".info-list-container dd");
		int availableItems = Integer.parseInt(elements.get(0).text());
		String from = elements.get(1).text().replace(" €", "").replace(',', '.');
		double trend = Double.parseDouble(elements.get(2).text().replace(" €", "").replace(',', '.'));

		MkmDeckInfo mkmDeckInfo = new MkmDeckInfo(deck);
		mkmDeckInfo.setAvailableItems(availableItems);
		mkmDeckInfo.setFrom(from);
		mkmDeckInfo.setTrend(trend);

		return mkmDeckInfo;
	}

}
