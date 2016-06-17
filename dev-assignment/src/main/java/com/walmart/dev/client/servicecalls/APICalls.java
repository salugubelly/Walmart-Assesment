package com.walmart.dev.client.servicecalls;

import java.net.URL;

import com.walmart.dev.client.HttpClient;

/**
 * @author Sinduja Alugubelly
 * Build url,
 * Receive JSON response 
 * Store it as String
 */
public class APICalls {

	private final static HttpClient clientResponse = new HttpClient();
	final static String apiKey = "x9wexx3ka4u99vp3yzhr9b9b"; // Api Key
	private static String url;

	/*
	 * This method will help us to get response
	 * related to item ID's.
	 */
	public static String getSearchAPI(String query) {
		url = "http://api.walmartlabs.com/v1/search?apiKey=" + apiKey + "&query=" + query;
		String searchResponse = clientResponse.get(url);
		return searchResponse;

	}

	/*
	 * This method will help us to get Product recommendations
	 * based on the item ID
	 */
	public static String getProductRecommendationAPI(String itemID) {
		url = "http://api.walmartlabs.com/v1/nbp?apiKey=" + apiKey + "&itemId=" + itemID;
		String recommendResponse = clientResponse.get(url);
		return recommendResponse;

	}

	/*
	 * This method will help us to get reviews
	 * based on recommended product ID.
	 */
	public static String getReviewsAPI(String itemID) {
		url = "http://api.walmartlabs.com/v1/reviews/" + itemID + "?apiKey=" + apiKey;
		String reviewResponse = clientResponse.get(url);
		return reviewResponse;

	}

}
