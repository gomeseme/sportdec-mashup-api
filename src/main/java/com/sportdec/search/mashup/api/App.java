package com.sportdec.search.mashup.api;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sportdec.search.mashup.api.domain.OutputType;
import com.sportdec.search.mashup.api.domain.UseCaseFactory;
import com.sportdec.search.mashup.api.domain.handlers.SearchHandler;
import com.sportdec.search.mashup.api.domain.services.GitHubApi;
import com.sportdec.search.mashup.api.domain.services.TwitterApi;

/**
 * 
 * @author emersongomes on 20/04/2018
 *
 */
public class App 
{
	final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
    		new App().run(args); 
    }
    
    public void run(String[] args) {
    		final AppConfiguration configuration = new AppConfiguration();
		final GitHubApi gitHubApi = new GitHubApi(configuration.getGitHubApiUrl());
		final TwitterApi twitterApi = new TwitterApi(configuration.getTwitterApiUrl(), configuration.getTwitterApiKey(), configuration.getTwitterApiSecret());
		final UseCaseFactory factory = new UseCaseFactory(gitHubApi, twitterApi);
		
		initRoutes(factory, args);
    }
    
    public void initRoutes(UseCaseFactory factory, String[] args) {
    		Options options = new Options();

        Option search = new Option("k", "keyword", true, "Insert a keyword to search projects and its latest tweets");
        search.setRequired(true);
        options.addOption(search);

        Option output = new Option("o", "output", true, "Set output type text=Standard Output / json=Json format");
        output.setRequired(false);
        options.addOption(output);
        
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
        		logger.error(e.getMessage(), e);
        		System.out.println(e.getMessage());
            formatter.printHelp("help", options);
            System.exit(1);
            return;
        }

        final String keywordToSearch = cmd.getOptionValue("keyword");
        String outputTyped = cmd.getOptionValue("output");
        OutputType outputFormat = OutputType.TEXT; 
        
        	try {
        		outputFormat = OutputType.valueOf(outputTyped.toUpperCase());
    		}catch(Exception e) {
    			logger.error("Output Type was not informed or doesn't exist. Using Text as default");
    		}
        
        new SearchHandler(factory).process(keywordToSearch, outputFormat);
    }
}
