package com.abyeti.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.abyeti.plagiarism.PlagiarismChecker;
import org.json.simple.JSONObject;

import com.abyeti.model.GitHubData;
import com.abyeti.services.HandlerGitHub;
import com.abyeti.services.HandlerHackerRank;
import java.io.IOException;

/**
 * @author vipulchasta
 *
 */
@Path("/srv")
public class RequestService {

	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject restServiceInfo() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ServiceName", "RequestService");

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/GitHub/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject restServiceGitHubInfo() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ServiceName", "GitHubRequestService");
		jsonObject.put("sample", "/GitHub/{apiType}/{apiData}");

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/GitHub/{apiType}/{apiData}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object restGetGitHubData(@PathParam("apiType") String apiType, @PathParam("apiData") String apiData) {

		GitHubData githubData;

		if (apiType.equals("email")) {
			githubData = HandlerGitHub.getUserDataByEmail(apiData);
		} else if (apiType.equals("username")) {
			githubData = HandlerGitHub.getUserDataByUsername(apiData);
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ServiceName", "GitHubRequestService");
			jsonObject.put("sample", "/GitHub/{apiType}/{apiData}");
			return jsonObject;
		}

		return githubData;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/HackerRank")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject restServiceHackerRankInfo() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ServiceName", "HackerRankRequestService");
		jsonObject.put("sample", "/HackerRank/{apiType}/{apiData}");

		return jsonObject;
	}
	@SuppressWarnings("unchecked")
	@GET
	@Path("/HackerRank/{apiType}/{apiData}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject restGetHackerRankData(@PathParam("apiType") String apiType,
			@PathParam("apiData") String apiData) {

		JSONObject jsonObject;

		if (apiType.equals("profile")) {
			jsonObject = HandlerHackerRank.getUserProfileByUsername(apiData);
		} else if (apiType.equals("badges")) {
			jsonObject = HandlerHackerRank.getUserBadgesByUsername(apiData);
		} else if (apiType.equals("submissionHistory")) {
			jsonObject = HandlerHackerRank.getUserSubmissionHistoryByUsername(apiData);
		} else {
			jsonObject = new JSONObject();
			jsonObject.put("ServiceName", "HackerRankRequestService");
			jsonObject.put("sample", "/HackerRank/{apiType}/{apiData}");
			return jsonObject;
		}

		return jsonObject;
	}

	@GET
	@Path("/plagiarism/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getPlagiarsimReport(@QueryParam("dirpath") String dirpath) throws IOException {
		JSONObject jsonObject = PlagiarismChecker.getReportsForDirectory(dirpath);
		return jsonObject;
	}
}
