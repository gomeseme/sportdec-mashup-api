package com.sportdec.search.mashup.api.domain.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sportdec.search.mashup.api.domain.JsonGetProperty;
import com.sportdec.search.mashup.api.domain.Repository;
import com.sportdec.search.mashup.api.domain.exception.ServiceApiException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GitHubApi implements GitHubApiClient, JsonGetProperty {

	final Logger logger = LoggerFactory.getLogger(GitHubApi.class);

	private OkHttpClient httpClient;
	private String serviceUrl;
	private JsonParser jsonParser;
	
	private static final String SEARCH_REPOSITORIES_ENDPOINT = "/search/repositories";
		
	public GitHubApi(String serviceUrl) {
		this.httpClient = new OkHttpClient();
		this.serviceUrl = serviceUrl;
		this.jsonParser = new JsonParser();
	}
	
	public List<Repository> searchRepositories(String keyword) throws IOException {
		List<Repository> repositories = new ArrayList<>();
		
		HttpUrl url = HttpUrl.get(new URL(this.serviceUrl + SEARCH_REPOSITORIES_ENDPOINT))
                .newBuilder()
                .addQueryParameter("q", keyword)
                .addQueryParameter("sort", "stars")
                .addQueryParameter("order", "desc")
                .build();
		
		Request request = new Request.Builder()
				.url(url)
				.get()
				.build();
		
		logger.debug("Calling GitHub API. " + SEARCH_REPOSITORIES_ENDPOINT);
        Response response = httpClient.newCall(request).execute();
        
        if (response.isSuccessful()) {
    			JsonObject root = jsonParser.parse(response.body().string()).getAsJsonObject();
    			JsonArray items = root.getAsJsonArray("items");
        		
    			items.forEach(item -> {
				JsonObject data = item.getAsJsonObject();
				
    				Repository repo = new Repository();
    				repo.setName(getStringFromJson(data.get("name")));
    				repo.setDescription(getStringFromJson(data.get("description")));
    				repo.setUrl(getStringFromJson(data.get("html_url")));
    				repo.setCreateDate(getDateFromJson(data.get("created_at"), repo.dateFormat));
    				
    				JsonObject owner = data.get("owner").getAsJsonObject();
    				repo.setOwner(getStringFromJson(owner.get("login")));
    				
    				repositories.add(repo);
    			});    			
	    } else if (response.code() == 500) {
	    		throw new ServiceApiException("GitHub API returned http 500: " + response.message());
	    }
		return repositories;
	}

}
