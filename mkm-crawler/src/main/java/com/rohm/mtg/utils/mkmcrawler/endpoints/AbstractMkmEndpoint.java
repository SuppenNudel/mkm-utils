package com.rohm.mtg.utils.mkmcrawler.endpoints;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class AbstractMkmEndpoint<T> {

	private static final String URI = "https://www.cardmarket.com/en/";
//	private static final String encoding = "ISO-8859-1";

	protected WebTarget target;
	private GenericType<T> resultType;

	private static int waitSeconds = 5;
	private static int sleepMs = 2500;
	private static LocalDateTime lastRequest = LocalDateTime.now().minusSeconds(waitSeconds);

	protected AbstractMkmEndpoint(String path, GenericType<T> resultType) {
		Client client = ClientBuilder.newClient();
		target = client.target(URI).register(JacksonJsonProvider.class).path(path);
		this.resultType = resultType;
	}

	protected AbstractMkmEndpoint(String path, Class<T> resultClass) {
		this(path, new GenericType<T>(resultClass));
	}

	protected GenericType<T> getResultType() {
		return resultType;
	}

	public T getByJsoup() throws IOException {
		String url = target.getUri().toString();
		org.jsoup.Connection.Response execute = Jsoup.connect(url).followRedirects(true).execute();
		Document parse = Jsoup.parse(execute.body());
		boolean contains = parse.toString().contains("Eventide");
		return null;
	}

	public T get() {
		synchronized (lastRequest) {
			while(lastRequest.plusSeconds(5).isAfter(LocalDateTime.now())) {
				System.out.println("last request less than "+waitSeconds+" seconds ago, waiting "+sleepMs+" ms");
				try {
					Thread.sleep(sleepMs);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lastRequest = LocalDateTime.now();
		}
		System.out.println(target.getUri());
		return handleResponse(target
		.request().get());
	}

	protected T handleResponse(Response response) {
		T result = null;
		try (response) {
			if(response.getStatus() == Status.OK.getStatusCode()) {
				result = parseReponse(response);
			} else {
				System.err.println(response.getStatus()+" "+response.getStatusInfo());
			}
		}
		return result;
	}

	protected T parseReponse(Response response) {
		return response.readEntity(resultType);
	}

	protected void resetQueryParam(String key) {
		target = target.queryParam(key, new Object[0]);
	}

}
