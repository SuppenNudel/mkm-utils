package com.rohm.mtg.utils.mkmcrawler.endpoints;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.rohm.mtg.utils.mkmcrawler.model.Condition;
import com.rohm.mtg.utils.mkmcrawler.model.Language;
import com.rohm.mtg.utils.mkmcrawler.model.MkmCardInfo;
import com.rohm.mtg.utils.mkmcrawler.model.SellerCountry;


public class MkmCardsEndpoint extends AbstractMkmEndpoint<MkmCardInfo> {

	private String cardName;

	public MkmCardsEndpoint(String cardName) {
		super("Magic/Cards/"+cleanCardName(cardName), MkmCardInfo.class);
		this.cardName = cardName;
	}

	private static String cleanCardName(String cardName) {
		return cardName;
	}

	public MkmCardsEndpoint sellerCountry(SellerCountry... sellerCountries) {
		String key = "sellerCountry";
		resetQueryParam(key);
		Object[] sellerCountryIdsArray = Arrays.asList(sellerCountries).stream().map(SellerCountry::id).collect(Collectors.toList()).toArray();
		target = target.queryParam(key, sellerCountryIdsArray);
		return this;
	}

	public MkmCardsEndpoint language(Language... languages) {
		String key = "language";
		resetQueryParam(key);
		Object[] languageIdsArray = Arrays.asList(languages).stream().map(Language::id).collect(Collectors.toList()).toArray();
		target = target.queryParam(key, languageIdsArray);
		return this;
	}

	public MkmCardsEndpoint minCondition(Condition condition) {
		String key = "condition";
		resetQueryParam(key);
		target = target.queryParam(key, condition.id());
		return this;
	}

	@Override
	protected MkmCardInfo parseReponse(Response response) {
		String html = response.readEntity(String.class);
		MkmCardInfo mkmCardInfo = parseDocumentForMkmCardInfo(html);
		return mkmCardInfo;
	}

	private MkmCardInfo parseDocumentForMkmCardInfo(String html) {
		Document document = Jsoup.parse(html);
		Elements elements = document.select("section[id=info] dd");
		int availableItems = Integer.parseInt(elements.get(0).text());
		int versions = Integer.parseInt(elements.get(1).text());
		double from = Double.parseDouble(elements.get(2).text().replace(" €", "").replace(',', '.'));
		double trend = Double.parseDouble(elements.get(3).text().replace(" €", "").replace(',', '.'));

		MkmCardInfo mkmCardInfo = new MkmCardInfo(cardName);
		mkmCardInfo.setAvailableItems(availableItems);
		mkmCardInfo.setVersions(versions);
		mkmCardInfo.setFrom(from);
		mkmCardInfo.setTrend(trend);

		return mkmCardInfo;
	}

}
