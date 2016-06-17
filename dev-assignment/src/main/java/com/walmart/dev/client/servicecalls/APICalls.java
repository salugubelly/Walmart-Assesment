package com.walmart.dev.client.servicecalls;

import com.walmart.dev.client.RestClient;

/**
 * @author Sinduja Alugubelly 
 *  Build url to API,
 *  Receive JSON response Store it as String 
 *  returns JSON response as a String
 */
public class APICalls {

	private final static RestClient clientResponse = new RestClient();
	final static String apiKey = "x9wexx3ka4u99vp3yzhr9b9b"; // Api Key
	private static String url;

	/*
	 * This method will help us to get response related to item ID based on
	 * given query string from Walmart SearchAPI.
	 */
	public static String getSearchAPI(String query) {
		url = "http://api.walmartlabs.com/v1/search?apiKey=" + apiKey + "&query=" + query;
		String searchResponse = clientResponse.get(url);
		return searchResponse;

	}

	/*
	 * This method will help us to get response related to Product
	 * recommendations based on the given itemID(retrieved from SearchAPI) from
	 * ProductRecommendationsAPI.
	 */
	public static String getProductRecommendationAPI(String itemID) {
		url = "http://api.walmartlabs.com/v1/nbp?apiKey=" + apiKey + "&itemId=" + itemID;
		String recommendResponse = clientResponse.get(url);
		return recommendResponse;

	}

	/*
	 * This method will help us to get response related to product reviews for
	 * every recommended product ID(retrieved from Product RecommendationsAPI)
	 * from ReviewsAPI.
	 */
	public static String getReviewsAPI(String itemID) {
		url = "http://api.walmartlabs.com/v1/reviews/" + itemID + "?apiKey=" + apiKey;
		String reviewResponse = clientResponse.get(url);
		return reviewResponse;

	}

}
