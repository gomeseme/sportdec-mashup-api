package com.sportdec.search.mashup.api.domain.services;

import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sportdec.search.mashup.api.domain.Tweet;

public class TwitterApiInMemory implements TwitterApiClient {

	private Calendar calendar = Calendar.getInstance();
	
	public TwitterApiInMemory() {
		
	}
	
	@Override
	public List<Tweet> searchTweets(String keyword) throws IOException {
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		Tweet tweet = new Tweet();
		tweet.setText("Tweet 1");
		calendar.set(2018, Month.APRIL.ordinal(), 22, 22, 50, 00);
		tweet.setCreatedAt(calendar.getTime());
		tweet.setUser("user1");
		tweet.setUserUrl("htttp://t.co/user1");
		tweets.add(tweet);
		
		tweet = new Tweet();
		tweet.setText("Tweet 2");
		calendar.set(2018, Month.MARCH.ordinal(), 21, 13, 25, 10);
		tweet.setCreatedAt(calendar.getTime());
		tweet.setUser("user2");
		tweet.setUserUrl("htttp://t.co/user2");
		tweets.add(tweet);
		
		return tweets;
	}

}
