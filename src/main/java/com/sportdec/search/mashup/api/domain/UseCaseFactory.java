package com.sportdec.search.mashup.api.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sportdec.search.mashup.api.domain.services.GitHubApiClient;
import com.sportdec.search.mashup.api.domain.services.TwitterApiClient;
import com.sportdec.search.mashup.api.domain.usecases.SearchProjectsAndTweetsUseCase;

public class UseCaseFactory {
	
	final Logger logger = LoggerFactory.getLogger(UseCaseFactory.class);

	private final GitHubApiClient gitHubApi;
	private final TwitterApiClient twitterApi;
	
	public UseCaseFactory(GitHubApiClient gitHubApi, TwitterApiClient twitterApi) {
		this.gitHubApi = gitHubApi;
		this.twitterApi = twitterApi;
	}
	
	public SearchProjectsAndTweetsUseCase newSearchProjectAndTweetsUseCase(String keyword) {
		return new SearchProjectsAndTweetsUseCase(gitHubApi, twitterApi, keyword);
	}
}
