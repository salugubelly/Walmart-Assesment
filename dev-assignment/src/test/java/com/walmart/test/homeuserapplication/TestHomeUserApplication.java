package com.walmart.test.homeuserapplication;

import java.util.HashMap;

import org.junit.Test;

import com.walmart.dev.client.servicecalls.APICalls;
import com.walmart.dev.homeworkapplication.UserRecommendations;

import junit.framework.Assert;

/**
 * @author Sinduja Alugubelly
 *
 */
public class TestHomeUserApplication {

	private final UserRecommendations userReviews = new UserRecommendations();
	
	@Test
	public void testHomeUserApplicationFunctionality() throws Exception {
		String query = "ipod";
		HashMap<String, Double> unsortedReviewMap = new HashMap();
		HashMap<String, Double> sortedReviewMap = new HashMap();
		HashMap<String, Double> expectedMap = new HashMap();
		expectedMap.put("25857866", 5.0);
		expectedMap.put("30146246", 4.78);
		expectedMap.put("31232984", 4.55);
		expectedMap.put("42608125", 4.54);
		expectedMap.put("42608106", 4.42);
		expectedMap.put("45804389", 4.23);
		expectedMap.put("39875894", 4.14);
		expectedMap.put("45804400", 3.97);
		expectedMap.put("44460683", 3.37);
		expectedMap.put("42807912", 0.0);
		String searchResponseJson = APICalls.getSearchAPI(query);
		String productID = userReviews.getItemID(searchResponseJson);
		String recommendResponseJson = APICalls.getProductRecommendationAPI(productID);
		unsortedReviewMap = userReviews.createReviewMap(recommendResponseJson);
		sortedReviewMap = userReviews.sortByComparator(unsortedReviewMap);
		userReviews.printMap(sortedReviewMap);
		Assert.assertEquals("Expected and actual results are not same",expectedMap.entrySet(), userReviews.sortByComparator(unsortedReviewMap).entrySet());
		
	}

	@Test
	public void testNullChecks() throws Exception {
		String query = "ipod";
		HashMap<String, Double> unsortedReviewMap = new HashMap();
		HashMap<String, Double> sortedReviewMap = new HashMap();
		
		String searchResponseJson = APICalls.getSearchAPI(query);
		String productID = userReviews.getItemID(searchResponseJson);
		String recommendResponseJson = APICalls.getProductRecommendationAPI(productID);
		unsortedReviewMap = userReviews.createReviewMap(recommendResponseJson);
		sortedReviewMap = userReviews.sortByComparator(unsortedReviewMap);
		Assert.assertNotNull("Un sorted Review Map is null", unsortedReviewMap);
		Assert.assertNotNull("Sorted Review Map is null", sortedReviewMap);
	}
}
