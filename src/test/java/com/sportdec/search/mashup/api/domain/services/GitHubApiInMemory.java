package com.sportdec.search.mashup.api.domain.services;

import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sportdec.search.mashup.api.domain.Repository;

public class GitHubApiInMemory implements GitHubApiClient {

	private Calendar calendar = Calendar.getInstance();

	public GitHubApiInMemory() {
	
	}
	
	@Override
	public List<Repository> searchRepositories(String keyword) throws IOException {
		List<Repository> repositories = new ArrayList<Repository>();
		Repository repo = new Repository();
		repo.setName("FootballJSON");
		repo.setDescription("This is a sample Football matches search project");
		repo.setOwner("uefa");
		repo.setUrl("http://www.github.com");
		calendar.set(2017, Month.DECEMBER.ordinal(), 01);
		repo.setCreateDate(calendar.getTime());
		repositories.add(repo);
		
		return repositories;
	}

}
