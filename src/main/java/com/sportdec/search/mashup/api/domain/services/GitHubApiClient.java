package com.sportdec.search.mashup.api.domain.services;

import java.io.IOException;
import java.util.List;

import com.sportdec.search.mashup.api.domain.Repository;

public interface GitHubApiClient {
	public List<Repository> searchRepositories(String keyword) throws IOException;
}
