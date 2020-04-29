package com.abyeti.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GitHubRepositoryData")
public class GitHubRepositoryData {

	String repositoryName;
	Integer commitCount = 0;
	Integer commitContributionByUser = 0;
	Set<String> techStack = new HashSet<String>();

	// Repository Reusability
	Integer repoForkCount = 0;

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public Integer getCommitCount() {
		return commitCount;
	}

	public void setCommitCount(Integer commitCount) {
		this.commitCount = commitCount;
	}

	public Integer getCommitContributionByUser() {
		return commitContributionByUser;
	}

	public void setCommitContributionByUser(Integer commitContributionByUser) {
		this.commitContributionByUser = commitContributionByUser;
	}

	public Integer getRepoForkCount() {
		return repoForkCount;
	}

	public void setRepoForkCount(Integer repoForkCount) {
		this.repoForkCount = repoForkCount;
	}

	public Set<String> getTechStack() {
		return techStack;
	}

	public void setTechStack(Set<String> techStack) {
		this.techStack = techStack;
	}
	
	public void addTechStack(String techName) {
		if(techName != null && !techName.equals("null")) {
			this.techStack.add(techName);
		}
	}
}
