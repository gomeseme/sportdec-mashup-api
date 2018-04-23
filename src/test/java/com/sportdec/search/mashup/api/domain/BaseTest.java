package com.sportdec.search.mashup.api.domain;

import com.sportdec.search.mashup.api.domain.services.GitHubApiClient;
import com.sportdec.search.mashup.api.domain.services.GitHubApiInMemory;
import com.sportdec.search.mashup.api.domain.services.TwitterApiClient;
import com.sportdec.search.mashup.api.domain.services.TwitterApiInMemory;

public abstract class BaseTest {
	protected UseCaseFactory factory;
	protected GitHubApiClient gitHubApi;
	protected TwitterApiClient twitterApi;
	
	protected void setup() {
		gitHubApi = new GitHubApiInMemory();
		twitterApi = new TwitterApiInMemory();
		
		factory = new UseCaseFactory(gitHubApi, twitterApi);
	}
}
