package com.abyeti.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GitHubData")
public class GitHubData {

	String username;
	Double score;
	Map<String, GitHubRepositoryData> repositories;
	Set<String> techStack = new HashSet<String>();

	String message = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Map<String, GitHubRepositoryData> getRepos() {
		return repositories;
	}

	public void setRepos(Map<String, GitHubRepositoryData> repos) {
		this.repositories = repos;
	}

	public Set<String> getTechStack() {
		return techStack;
	}

	public void setTechStack(Set<String> techStack) {
		this.techStack = techStack;
	}
	
	public void addTechStack(String techName) {
		this.techStack.add(techName);
	}

}
