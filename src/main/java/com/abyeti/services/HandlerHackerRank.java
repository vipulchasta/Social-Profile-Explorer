package com.abyeti.services;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author ishan_pahare
 *
 */
public class HandlerHackerRank {

	static Map<String, String> API_HACKERRANK_DATA = new HashMap<String, String>();
	static boolean isInitialized = false;

	private static void moduleInit() {

		// TODO: Rightnow hackerrank data is hardcoded, these data are obtained by
		// python script
		API_HACKERRANK_DATA.put("ishan_pahare",
				"{\"id\": \"bcfa4c1edc4811e9973874867a120114\", \"username\": \"ishan_pahare\", \"name\": \"Ishan Pahare\", \"skills\": [\"Problem Solving\", \"Days of Code\", \"Sql\", \"C language\"], \"total_badges\": 4}");
		API_HACKERRANK_DATA.put("raghavendramj123",
				"{\"id\": \"d5dc502cdc4811e9a1cc74867a120114\", \"username\": \"raghavendramj123\", \"name\": \"Raghavendra M J\", \"skills\": [\"Problem Solving\"], \"total_badges\": 1}");
		API_HACKERRANK_DATA.put("shubham18rastogi",
				"{\"id\": \"e2959cb6dc4811e9877574867a120114\", \"username\": \"shubham18rastogi\", \"name\": \"Shubham Rastogi\", \"skills\": [\"Problem Solving\", \"CPP\", \"Sql\"], \"total_badges\": 3}");
		API_HACKERRANK_DATA.put("utkarsh_mathur_1",
				"{\"id\": \"edbfffb4dc4811e99f2e74867a120114\", \"username\": \"utkarsh_mathur_1\", \"name\": \"Utkarsh Mathur\", \"skills\": [\"Problem Solving\"], \"total_badges\": 1}");
		API_HACKERRANK_DATA.put("vipulchasta",
				"{\"id\": \"c9310da4dc4811e9b52a74867a120114\", \"username\": \"vipulchasta\", \"name\": \"Vipul Chasta\", \"skills\": [\"Problem Solving\", \"CPP\", \"Java\", \"C language\"], \"total_badges\": 4}");

		isInitialized = true;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject getUserData(String username) {

		if (!isInitialized) {
			moduleInit();
		}
		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();

		try {
			String userDataInJSON = API_HACKERRANK_DATA.get(username);
			if (userDataInJSON != null)
				jsonObject = (JSONObject) parser.parse(userDataInJSON);
			else
				jsonObject.put("message", "User Has No Activity");
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("message", "User Has No Activity");
		}

		return jsonObject;
	}
}
