package com.walmart.dev.homeworkapplication;

import java.util.HashMap;

import java.util.logging.Logger;

import com.walmart.dev.client.servicecalls.APICalls;

/**
 * @author Sinduja Alugubelly
 * Program to rank-order Walmart product recommendations based upon customer reviews
 */
public class UserRecommendationsMain {

	private static final Logger log = Logger.getLogger(UserRecommendationsMain.class.getName());
	private static final APICalls apiCalls = new APICalls();
	private static final UserRecommendations userRec = new UserRecommendations();

	public static void main(String args[]) {
		
		getRecommendations();

	}

	public static void getRecommendations() {

		String query = "ipod";
		if (!query.isEmpty()) {
			HashMap<String, Double> unsortedReviewMap = new HashMap();
			HashMap<String, Double> sortedReviewMap = new HashMap();
			String recommendResponseJson = "";
			/*
			 * Get call to search API by passing query as String Parse first
			 * Item Id from JSON response
			 */
			String searchResponseJson = apiCalls.getSearchAPI(query);
			String productID = userRec.getItemID(searchResponseJson);
			/*
			 * Get call to Recommendation Products API by passing Item ID
			 */
			if (!productID.isEmpty()) {
				recommendResponseJson = APICalls.getProductRecommendationAPI(productID);
			}
			/*
			 * Build Unsorted review map <ItemID,OverallAverageRating> Sort the
			 * Map using Values
			 */
			try {
				unsortedReviewMap = userRec.createReviewMap(recommendResponseJson);
				sortedReviewMap = userRec.sortByComparator(unsortedReviewMap);
				userRec.printMap(sortedReviewMap); // Print HashMap
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			log.info("Empty query string entered");
	}

}
