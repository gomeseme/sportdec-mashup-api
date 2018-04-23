package com.sportdec.search.mashup.api.domain.services;

import java.io.IOException;
import java.util.List;

import com.sportdec.search.mashup.api.domain.Tweet;

public interface TwitterApiClient {
	public List<Tweet> searchTweets(String keyword) throws IOException;
}
