package com.abyeti.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.abyeti.model.GitHubData;
import com.abyeti.model.GitHubRepositoryData;
import com.abyeti.util.Util;

/**
 * @author vipulchasta
 *
 */
public class HandlerGitHub {

	// final static String API_GitSearch =
	// "https://api.github.com/search/users?q=$USER_EMAIL$+in:email";
	// final static String API_ClientID = "a7c81ded87b1cc462ed1";
	// final static String API_ClientSecret =
	// "4969c55ab90af58e22fdbb77a0a48d9b8eb4c075";
	// final static String API_AppendEnd =
	// "?client_id=$API_CLIENT_ID$&client_secret=$API_CLIENT_SECRET$";

	final static String API_GITHUB_EMAIL = "https://api.github.com/search/users?q=$USER_EMAIL$+in:email?client_id=$API_CLIENT_ID$&client_secret=$API_CLIENT_SECRET$";
	final static String API_GITHUB_USERNAME = "https://api.github.com/search/users?q=$USER_USERNAME$+in:username?client_id=$API_CLIENT_ID$&client_secret=$API_CLIENT_SECRET$";
	final static String API_GITHUB_APPEND = "?client_id=$API_CLIENT_ID$&client_secret=$API_CLIENT_SECRET$";

	static boolean isPoolInitialized = false;
	static Map<String, String> API_POOL_CLIENT_MAP = new HashMap<String, String>();
	static List<String> API_POOL_CLIENT_LIST = new ArrayList<String>();

	static Integer API_HIT_COUNTER = 0;
	static Integer API_CLIENT_INDEX = 0;

	private static void initPool() {
		API_POOL_CLIENT_MAP.put("a7c81ded87b1cc462ed1", "4969c55ab90af58e22fdbb77a0a48d9b8eb4c075");
		API_POOL_CLIENT_MAP.put("e9a53645ffa18e71e29e", "fb9e4dec5ceb9819cc53ff609bcbbe999034e752");

		for (String client : API_POOL_CLIENT_MAP.keySet()) {
			API_POOL_CLIENT_LIST.add(client);
		}

		isPoolInitialized = true;
	}

	private static String getApiAppender() {
		String str = null;

		if (!isPoolInitialized) {
			initPool();
		}

		if (API_CLIENT_INDEX >= API_POOL_CLIENT_LIST.size()) {
			API_CLIENT_INDEX = 0;
		}

		str = API_GITHUB_APPEND.replace("$API_CLIENT_ID$", API_POOL_CLIENT_LIST.get(API_CLIENT_INDEX));
		str = str.replace("$API_CLIENT_SECRET$", API_POOL_CLIENT_MAP.get(API_POOL_CLIENT_LIST.get(API_CLIENT_INDEX)));

		API_CLIENT_INDEX++;
		API_HIT_COUNTER++;

		System.out.println("API_HIT_COUNTER: " + API_HIT_COUNTER + ", API_CLIENT_INDEX: " + API_CLIENT_INDEX);
		return str;
	}

	@SuppressWarnings("unchecked")
	public static GitHubData getUserDataByEmail(String emailId) {

		GitHubData gitHubData = new GitHubData();
		Map<String, GitHubRepositoryData> repoMap = new HashMap<String, GitHubRepositoryData>();

		String myRequest = API_GITHUB_EMAIL.replace("$USER_EMAIL$", emailId);

		Client client = ClientBuilder.newClient();

		JSONObject jsonObject = new JSONObject();
		jsonObject = client.target(myRequest + getApiAppender()).request(MediaType.APPLICATION_JSON)
				.get(JSONObject.class);

		Integer found = (Integer) jsonObject.get("total_count");
		if (found == 1) {
			ArrayList<HashMap<String, Object>> jsonArray = (ArrayList<HashMap<String, Object>>) jsonObject.get("items");
			HashMap<String, Object> userProfile = jsonArray.get(0);

			String userName = (String) userProfile.get("login");
			String reposUrl = (String) userProfile.get("repos_url");
			Double score = (Double) userProfile.get("score");

			gitHubData.setUsername(userName);
			gitHubData.setScore(score);
			gitHubData.setRepos(repoMap);

			Map<String, String> reposAndBranchesLink = getReposAndBranchesLink(repoMap, reposUrl);
			Map<String, String> reposAndTopCommitLink = getReposAndTopCommitLink(repoMap, reposAndBranchesLink);

			// jsonResponse.put("reposAndBranchesLink", reposAndBranchesLink);
			// jsonResponse.put("reposAndTopCommitLink", reposAndTopCommitLink);

			for (String repos : reposAndTopCommitLink.keySet()) {
				parseCommitAndItsParanetCommit(userName, repoMap.get(repos), reposAndTopCommitLink.get(repos));
			}

			for (String repoName : repoMap.keySet()) {
				GitHubRepositoryData repo = repoMap.get(repoName);
				for (String tech : repo.getTechStack()) {
					gitHubData.addTechStack(tech);
				}
			}

		} else {
			// jsonResponse.put("message", "No User Activity");
		}

		return gitHubData;
	}

	@SuppressWarnings("unchecked")
	public static GitHubData getUserDataByUsername(String username) {

		GitHubData gitHubData = new GitHubData();
		Map<String, GitHubRepositoryData> repoMap = new HashMap<String, GitHubRepositoryData>();

		String myRequest = API_GITHUB_USERNAME.replace("$USER_USERNAME$", username);

		Client client = ClientBuilder.newClient();

		JSONObject jsonObject = new JSONObject();
		jsonObject = client.target(myRequest + getApiAppender()).request(MediaType.APPLICATION_JSON)
				.get(JSONObject.class);

		Integer found = (Integer) jsonObject.get("total_count");
		if (found == 1) {
			ArrayList<HashMap<String, Object>> jsonArray = (ArrayList<HashMap<String, Object>>) jsonObject.get("items");
			HashMap<String, Object> userProfile = jsonArray.get(0);

			String userName = (String) userProfile.get("login");
			String reposUrl = (String) userProfile.get("repos_url");
			Double score = (Double) userProfile.get("score");

			gitHubData.setUsername(userName);
			gitHubData.setScore(score);
			gitHubData.setRepos(repoMap);

			Map<String, String> reposAndBranchesLink = getReposAndBranchesLink(repoMap, reposUrl);
			Map<String, String> reposAndTopCommitLink = getReposAndTopCommitLink(repoMap, reposAndBranchesLink);

			// jsonResponse.put("reposAndBranchesLink", reposAndBranchesLink);
			// jsonResponse.put("reposAndTopCommitLink", reposAndTopCommitLink);

			for (String repos : reposAndTopCommitLink.keySet()) {
				parseCommitAndItsParanetCommit(userName, repoMap.get(repos), reposAndTopCommitLink.get(repos));
			}

			for (String repoName : repoMap.keySet()) {
				GitHubRepositoryData repo = repoMap.get(repoName);
				for (String tech : repo.getTechStack()) {
					gitHubData.addTechStack(tech);
				}
			}

		} else {
			// jsonResponse.put("message", "No User Activity");
		}

		return gitHubData;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getReposAndBranchesLink(Map<String, GitHubRepositoryData> repoMap,
			String reposUrl) {
		Map<String, String> map = new HashMap<String, String>();

		Client client = ClientBuilder.newClient();
		JSONArray jsonArray = new JSONArray();
		jsonArray = client.target(reposUrl + getApiAppender()).request(MediaType.APPLICATION_JSON).get(JSONArray.class);

		// System.out.println(" ======> " + jsonArray.size());
		for (int i = 0; i < jsonArray.size(); i++) {
			Map<String, Object> jsonObject = (HashMap<String, Object>) jsonArray.get(i);

			String str = (String) jsonObject.get("branches_url");
			str = str.replace("{/branch}", "");
			map.put((String) jsonObject.get("name"), str);

			GitHubRepositoryData repo = new GitHubRepositoryData();
			repo.setRepositoryName((String) jsonObject.get("name"));
			repo.setRepoForkCount((Integer) jsonObject.get("forks_count"));
			repo.addTechStack((String) jsonObject.get("language"));
			repoMap.put((String) jsonObject.get("name"), repo);
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getReposAndTopCommitLink(Map<String, GitHubRepositoryData> repoMap,
			Map<String, String> reposAndBranchesLink) {
		Map<String, String> map = new HashMap<String, String>();

		Client client = ClientBuilder.newClient();

		for (String repoName : reposAndBranchesLink.keySet()) {
			String branchURL = reposAndBranchesLink.get(repoName);

			JSONArray jsonArray = new JSONArray();
			jsonArray = client.target(branchURL + getApiAppender()).request(MediaType.APPLICATION_JSON)
					.get(JSONArray.class);

			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> jsonObject = (HashMap<String, Object>) jsonArray.get(i);

				String branchName = (String) jsonObject.get("name");
				// TODO: RightNow Only Considering Master Branch, consider default branch
				if (branchName.equals("master")) {
					Map<String, Object> commit = (HashMap<String, Object>) jsonObject.get("commit");
					map.put(repoName, (String) commit.get("url"));
				}
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static void parseCommitAndItsParanetCommit(String userName, GitHubRepositoryData repo, String commitUrl) {

		if (commitUrl == null) {
			return;
		}

		// System.out.print("---> " + repo.getRepositoryName());
		// System.out.println("---> " + commitUrl);
		Client client = ClientBuilder.newClient();
		JSONObject jsonObject = new JSONObject();
		jsonObject = client.target(commitUrl + getApiAppender()).request(MediaType.APPLICATION_JSON)
				.get(JSONObject.class);

		Map<String, Object> committerData = (Map<String, Object>) jsonObject.get("committer");

		boolean isItThisUserCommit = false;

		if (committerData != null) {
			if (committerData.get("login").equals(userName)) {
				// System.out.println("=> Commit By: " + userName);
				isItThisUserCommit = true;
			} else if (committerData.get("login").equals("web-flow")) {
				// System.out.println("=> Commit By: " + userName + " Using web-flow");
				isItThisUserCommit = true;
			} else {
				System.out.println("=> Commit By Unknown Person (" + committerData.get("login") + ") : " + commitUrl);
			}
		} else {
			Map<String, Object> commitData = (Map<String, Object>) jsonObject.get("commit");
			committerData = (Map<String, Object>) commitData.get("committer");
			if (committerData.get("name").equals(userName)) {
				isItThisUserCommit = true;
			} else {
				System.out.println("=> Can not be identified the commiter for the commit");
			}
		}

		if (isItThisUserCommit) {
			List<Map<String, Object>> committedFiles = (List<Map<String, Object>>) jsonObject.get("files");
			for (Map<String, Object> fileData : committedFiles) {
				String fileName = (String) fileData.get("filename");
				// System.out.println("-----> " + fileName + " --> " +
				// Util.getTechStack(fileName));
				repo.addTechStack(Util.getTechStack(fileName));
			}
			repo.setCommitContributionByUser(repo.getCommitContributionByUser() + 1);
		}

		repo.setCommitCount(repo.getCommitCount() + 1);

		List<HashMap<String, Object>> parentsList = (List<HashMap<String, Object>>) jsonObject.get("parents");
		// TODO: visit all the parent and avoid the visited one
		/*
		 * for (HashMap<String, Object> parentCommit : parentsList) {
		 * parseCommitAndItsParanetCommit(userName, repo,
		 * (String)parentCommit.get("url")); }
		 */
		if (parentsList != null && parentsList.size() > 0) {
			HashMap<String, Object> parentCommit = parentsList.get(0);
			parseCommitAndItsParanetCommit(userName, repo, (String) parentCommit.get("url"));
		}
	}
}
