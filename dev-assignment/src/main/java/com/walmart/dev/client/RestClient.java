package com.walmart.dev.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


/**
 * @author Sinduja Alugubelly
 * This class establishes connection to 
 * Walmart.com Open API 
 * using Jersey Client
 */
public class RestClient {
	
	public static String get(String url) {

		Client client = Client.create();
		ClientResponse response = null;
		WebResource resource = client.resource(url);
		try {
			response = resource.accept("application/json").get(ClientResponse.class);
		} catch (Exception e) {
			e.getMessage();
		}
		return response.getEntity(String.class);
	}
}
