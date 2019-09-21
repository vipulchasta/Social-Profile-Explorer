package com.abyeti.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.abyeti.model.GitHubData;
import com.abyeti.services.HandlerGitHub;
import com.abyeti.services.HandlerHackerRank;

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

	
	
	@GET
	@Path("/GitHub/email/{emailId}")
	@Produces(MediaType.APPLICATION_JSON)
	public GitHubData restGetGitHubDataByEmail(@PathParam("emailId") String emailId) {

		GitHubData githubData = HandlerGitHub.getUserData(emailId);

		return githubData;
	}

	@GET
	@Path("/GitHub/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public GitHubData restGetGitHubDataByUsername(@PathParam("username") String username) {

		GitHubData githubData = HandlerGitHub.getUserData(username);

		return githubData;
	}
	
	
	

	@GET
	@Path("/HackerRank/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject restGetHackerRankDataByUsername(@PathParam("username") String username) {

		JSONObject jsonObject = HandlerHackerRank.getUserData(username);

		return jsonObject;
	}
}
