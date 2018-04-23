package com.sportdec.search.mashup.api.domain.handlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sportdec.search.mashup.api.domain.OutputType;
import com.sportdec.search.mashup.api.domain.Repository;
import com.sportdec.search.mashup.api.domain.UseCaseFactory;

public class SearchHandler {
	
	private final UseCaseFactory factory;
	private Gson gson;
	private static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private DateFormat dateFormat;
	
	public SearchHandler(UseCaseFactory factory) {
		this.factory = factory;
		this.gson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation()
			.setDateFormat(JSON_DATE_FORMAT).create();
		
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
    public void process(String keyword, OutputType outputFormat) {
        List<Repository> repositories = factory.newSearchProjectAndTweetsUseCase(keyword).execute();
        
        if(outputFormat.equals(OutputType.JSON)) {
            System.out.println(gson.toJson(repositories));
            return;
        }
        
        System.out.println("----------- SPORTDEC SEARCH MASHUP API -----------");
        System.out.println(String.format("Keyword searched: %s", keyword));
        System.out.println(String.format("%d project(s) found: ", repositories.size()));
        System.out.println("--------------------------------------------------");
        
        repositories.forEach(repo -> {
        		System.out.println(String.format("Project: %s", repo.getName()));
        		System.out.println(String.format("Description: %s", repo.getName()));
        		System.out.println(String.format("Url: %s", repo.getUrl()));
        		System.out.println(String.format("Latest Tweets:		-> %d tweets were found.", repo.getLatestTweet().size()));
        		
        		repo.getLatestTweet().forEach(tweet -> {
        			System.out.println("	User: " + tweet.getUser());
        			System.out.println("	Date: " + dateFormat.format(tweet.getCreatedAt()));
        			System.out.println("	Tweet: " + tweet.getText());
        			System.out.println();
        		});
        		System.out.println("*******************************************");
        });
    }
}
