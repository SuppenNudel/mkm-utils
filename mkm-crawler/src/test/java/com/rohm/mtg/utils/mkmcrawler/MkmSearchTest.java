package com.rohm.mtg.utils.mkmcrawler;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.rohm.mtg.utils.mkmcrawler.model.MkmProduct;

public class MkmSearchTest {

	@Test
	public void themeDeckTest() throws IOException {
		String query = "Battle Blitz";
		MkmProduct mkmProduct = MkmCrawler.search(query).getByJsoup();
	}

}
