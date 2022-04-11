package com.rohm.mtg.utils.mkmcrawler.endpoints;

import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.rohm.mtg.utils.mkmcrawler.model.MkmProduct;


public class MkmSearchEndpoint extends AbstractMkmEndpoint<MkmProduct> {


	public MkmSearchEndpoint(String query) {
		super("Magic/Products/searchString"+query, MkmProduct.class);
	}

	@Override
	protected MkmProduct parseReponse(Response response) {
		String html = response.readEntity(String.class);

		Document document = Jsoup.parse(html);
		return parseDocument(document);
	}

	private MkmProduct parseDocument(Document doc) {
		Elements select = doc.select("div");
		boolean contains = doc.toString().contains("Eventide");
		MkmProduct mkmProduct = new MkmProduct();

		return mkmProduct;
	}

}
