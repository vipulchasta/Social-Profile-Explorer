package com.abyeti.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

/**
 * @author vipulchasta
 *
 */
public class HandlerHackerRank {

	final static String API_USER_PROFILE = "https://www.hackerrank.com/rest/contests/master/hackers/$USER_USERNAME$/profile";
	final static String API_USER_BADGES = "https://www.hackerrank.com/rest/hackers/$USER_USERNAME$/badges";
	final static String API_USER_SUBMISSION_HISTORY = "https://www.hackerrank.com/rest/hackers/vipulchasta/submission_histories";

	@SuppressWarnings("unchecked")
	public static JSONObject getUserProfileByUsername(String username) {

		JSONObject jsonObject = new JSONObject();

		try {
			String myRequest = API_USER_PROFILE.replace("$USER_USERNAME$", username);

			Client client = ClientBuilder.newClient();

			jsonObject = client.target(myRequest).request(MediaType.APPLICATION_JSON).get(JSONObject.class);

		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("message", "User Has No Activity");
		}

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject getUserBadgesByUsername(String username) {

		JSONObject jsonObject = new JSONObject();

		try {
			String myRequest = API_USER_BADGES.replace("$USER_USERNAME$", username);

			Client client = ClientBuilder.newClient();

			jsonObject = client.target(myRequest).request(MediaType.APPLICATION_JSON).get(JSONObject.class);

		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("message", "User Has No Activity");
		}

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject getUserSubmissionHistoryByUsername(String username) {

		JSONObject jsonObject = new JSONObject();

		try {
			String myRequest = API_USER_SUBMISSION_HISTORY.replace("$USER_USERNAME$", username);

			Client client = ClientBuilder.newClient();

			jsonObject = client.target(myRequest).request(MediaType.APPLICATION_JSON).get(JSONObject.class);

		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("message", "User Has No Activity");
		}

		return jsonObject;
	}
}
