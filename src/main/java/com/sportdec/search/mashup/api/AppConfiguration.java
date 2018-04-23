package com.sportdec.search.mashup.api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AppConfiguration {
	
	final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);
	private final Config conf;
	
	public String getTwitterApiUrl() {
		return conf.getString("twitter.api.url");
	}
	
	public String getTwitterApiKey() {
		return conf.getString("twitter.api.key");
	}
	
	public String getTwitterApiSecret() {
		return conf.getString("twitter.api.secret");
	}
	
	public String getGitHubApiUrl() {
		return conf.getString("github.api.url");
	}
	
	public AppConfiguration() {
		logger.info("Loading configuration file.");
		this.conf = ConfigFactory.load();
	}
}
