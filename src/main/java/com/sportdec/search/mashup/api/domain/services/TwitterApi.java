package com.sportdec.search.mashup.api.domain.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sportdec.search.mashup.api.domain.JsonGetProperty;
import com.sportdec.search.mashup.api.domain.Tweet;
import com.sportdec.search.mashup.api.domain.exception.ServiceApiException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TwitterApi implements TwitterApiClient, JsonGetProperty {

	final Logger logger = LoggerFactory.getLogger(TwitterApi.class);

	private OkHttpClient httpClient;
	private JsonParser jsonParser;
	private String serviceUrl;
	private String apiKey;
	private String apiSecret;
	private String token;
	
	private static final String OAUTH2_TOKEN_ENDPOINT = "/oauth2/token";
	private static final String SEARCH_TWEETS_ENDPOINT = "/1.1/search/tweets.json";

	public TwitterApi(String serviceUrl, String apiKey, String apiSecret) {
		this.httpClient = new OkHttpClient();
		this.jsonParser = new JsonParser();
		this.serviceUrl = serviceUrl;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}
	
	public List<Tweet> searchTweets(String keyword) throws IOException {
		List<Tweet> tweets = new ArrayList<>();
		HttpUrl url = HttpUrl.get(new URL(this.serviceUrl + SEARCH_TWEETS_ENDPOINT))
                .newBuilder()
                .addQueryParameter("q", keyword)
                .addQueryParameter("result_type", "mixed")
                .build();
		
		Request request = new Request.Builder()
				.header("Authorization", "Bearer " + getToken())
				.url(url)
				.get()
				.build();
		
		logger.debug("Calling Twitter API. " + SEARCH_TWEETS_ENDPOINT);
        Response response = httpClient.newCall(request).execute();
        
        if (response.isSuccessful()) {
    			JsonObject root = jsonParser.parse(response.body().string()).getAsJsonObject();
    			JsonArray statusesArray = root.getAsJsonArray("statuses");
        		
    			statusesArray.forEach(item -> {
				JsonObject data = item.getAsJsonObject();
				
    				Tweet tweet = new Tweet();
    				tweet.setText(getStringFromJson(data.get("text")));
    				tweet.setCreatedAt(getDateFromJson(data.get("created_at"), tweet.dateFormat));
    				
    				JsonObject user = data.get("user").getAsJsonObject();
    				tweet.setUser(getStringFromJson(user.get("name")));
    				tweet.setUserUrl(getStringFromJson(user.get("url")));
				
				tweets.add(tweet);
    			});    			
	    } else if (response.code() == 500) {
	        throw new ServiceApiException("Twitter SearchAPI returned http 500: " + response.message());
	    }
		return tweets;
	}

	private void authenticate() throws IOException {
		RequestBody formBody = new FormBody.Builder()
		        .add("grant_type", "client_credentials").build();
		        
		Request request = new Request.Builder()
				.header("Authorization", "Basic " + getBasicAuthorizationKey())
				.url(this.serviceUrl + OAUTH2_TOKEN_ENDPOINT)
				.post(formBody)
				.build();
		
		logger.debug("Calling Twitter API. " + OAUTH2_TOKEN_ENDPOINT);
        Response response = httpClient.newCall(request).execute();
        
        if (response.isSuccessful()) {
        		JsonObject jsonResponse = new JsonParser().parse(response.body().string()).getAsJsonObject();
        		this.token = jsonResponse.get("access_token").getAsString();
        } else if (response.code() == 403) {
            throw new ServiceApiException("Access denied for Twitter API. Check your credentials on application.conf file.");
        }
	}
	
	private String getBasicAuthorizationKey() {
		byte[] keyEncoded = Base64.getEncoder().encode(String.format("%s:%s", this.apiKey, this.apiSecret).getBytes());
		return new String(keyEncoded);
	}
	
	private String getToken() throws IOException {
		if(this.token == null)
			authenticate();
		
		return this.token;
	}
}