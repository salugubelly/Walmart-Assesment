package com.walmart.dev.homeworkapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.walmart.dev.client.servicecalls.APICalls;

/**
 * @author Sinduja Alugubelly
 * Model for the Application
 *
 */

public class UserRecommendations {

	private static final Logger log = Logger.getLogger(UserRecommendations.class.getName());
	
	/**
	 * Function to sort the Map by values
	 * @param unsorted Map
	 * @return sorted Map based on values 
	 */
	public static HashMap<String, Double> sortByComparator(Map<String, Double> unsortMap) {
		HashMap<String, Double> sortReviewMap = new LinkedHashMap();
		Set<Entry<String, Double>> set = unsortMap.entrySet();
		List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(set);
		
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
	
		for (Map.Entry<String, Double> entry : list) {
			sortReviewMap.put(entry.getKey(), entry.getValue());
		}

		return sortReviewMap;
	}

	/**
	 * Function to create the Map 
	 * @param Product Recommendation response in String format 																																		
	 * @return HashMap<ItemID,OverallAveragerating>
	 */
	public static HashMap<String, Double> createReviewMap(String recResponseJson) throws Exception {
		
		ArrayList<String> recommendResponseArr = getRecommendResponse(recResponseJson);
		HashMap<String, Double> revMap = new HashMap();
		for (int i = 0; i < recommendResponseArr.size(); i++) {
			String reviewResponseJson = APICalls.getReviewsAPI(recommendResponseArr.get(i));
			Double avgReview = getReviewAvrRating(reviewResponseJson);
			revMap.put(recommendResponseArr.get(i), avgReview);
		}
		return revMap;
	}

	/**
	 * Function to get overallaveragereviewrating for a particular recommended product
	 * @param RecommendedProductItemID 																																		
	 * @return averageOverallReviewRating
	 */
	public static Double getReviewAvrRating(String reResponseJson) {
		JSONParser parser = new JSONParser();
		String averageOverallRating = "";
		try {
			JSONObject obj = (JSONObject) parser.parse(reResponseJson);
			JSONObject reviewStatistics = (JSONObject) obj.get("reviewStatistics");
			if (reviewStatistics != null) {
				averageOverallRating = reviewStatistics.get("averageOverallRating").toString();
			} else {
				averageOverallRating = "0";

			}
		} catch (ParseException pe) {
			log.info(pe.getMessage());
		}
		return Double.parseDouble(averageOverallRating);
	}
	/**
	 * Function to parse recommended ItemList from ProductRecommendation API JSON response
	 * @param ProductRecommendAPI JSON response as String 																																		
	 * @return ProductRecommendation ItemList
	 */
	public static ArrayList<String> getRecommendResponse(String reviewResponse) throws Exception {

		JSONParser parser = new JSONParser();
		ArrayList<String> itemsList = new ArrayList();
		try {
			JSONArray array = (JSONArray) parser.parse(reviewResponse);
			for (int i = 0; i < array.size(); i++) {
				JSONObject itemObject = (JSONObject) array.get(i);
				String itemID = itemObject.get("itemId").toString();
				itemsList.add(itemID);
			}
		} catch (ParseException pe) {
			log.info(pe.getMessage());
		}
		return itemsList;
	}

	/**
	 * Function to parse ItemID from SearchAPI JSON response
	 * @param  SearchAPI JSON response as String																												
	 * @return itemId
	 */
	public static String getItemID(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject items = null;
		try {
			JSONObject obj = (JSONObject) parser.parse(jsonStr);
			JSONArray arr = (JSONArray) obj.get("items");
			items = (JSONObject) arr.get(0);
		} catch (ParseException pe) {
			log.info(pe.getMessage());
		}
		return items.get("itemId").toString();
	}

	/**
	 * Function to print HashMap<ItemID,OverallAvgrating>
	 * @param  Map																												
	 * @return void . Just Prints to the console.
	 */
	public static void printMap(Map<String, Double> map) {
		for (Entry<String, Double> entry : map.entrySet()) {
		System.out.println("ItemID : " + entry.getKey() + " Value : " + entry.getValue());
		}

	}
}
