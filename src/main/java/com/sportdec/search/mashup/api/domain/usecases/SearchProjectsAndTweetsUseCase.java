package com.sportdec.search.mashup.api.domain.usecases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sportdec.search.mashup.api.domain.Repository;
import com.sportdec.search.mashup.api.domain.Tweet;
import com.sportdec.search.mashup.api.domain.services.GitHubApiClient;
import com.sportdec.search.mashup.api.domain.services.TwitterApiClient;

public class SearchProjectsAndTweetsUseCase {

	final Logger logger = LoggerFactory.getLogger(SearchProjectsAndTweetsUseCase.class);

	private final GitHubApiClient gitHubApiClient;
	private final TwitterApiClient twitterApiClient;
	private final String keyword;
	private 	Map<String, List<Tweet>> tweetsByRepo;
	
	public SearchProjectsAndTweetsUseCase(GitHubApiClient gitHubApiClient, TwitterApiClient twitterApiClient, String keyword) {
		this.gitHubApiClient = gitHubApiClient;
		this.twitterApiClient = twitterApiClient;
		this.keyword = keyword;
	}
	
	public List<Repository> execute() {
		logger.info("Executing SearchProjectsAndTweetsUseCase.");
		List<Repository> repositories = new ArrayList<Repository>();
		
		try { 
			logger.info("Searching GitHub repositories with keyword: " + keyword);
			repositories = gitHubApiClient.searchRepositories(keyword);
			
			if(repositories.size() >= 10)
				repositories = repositories.subList(0, 9);
			
			tweetsByRepo = new HashMap<>();
			for(Repository repo : repositories) {
				searchTweetsForRepo(repo);
			}
			
		} catch (IOException e) {
			logger.error("An error occurred on searching GitHub repositories for: " + keyword, e);
		}
		return repositories;
	}
	
	private Repository searchTweetsForRepo(Repository repo) {
		try {					
			List<Tweet> tweets = null;
			if(!tweetsByRepo.containsKey(repo.getName())) {
				logger.info("Searching latest tweets using keyword: " + repo.getName());
				tweets = twitterApiClient.searchTweets(repo.getName());
				tweetsByRepo.put(repo.getName(), tweets);
			} else {
				logger.info("Getting latest tweets from memory using keyword: " + repo.getName());
				tweets = tweetsByRepo.get(repo.getName());
			}
			repo.setLatestTweet(tweets);
		} catch(IOException e) {
			logger.error("An error occurred on searching latest tweets for " + repo.getName(), e);
		}
		return repo;
	}
}
