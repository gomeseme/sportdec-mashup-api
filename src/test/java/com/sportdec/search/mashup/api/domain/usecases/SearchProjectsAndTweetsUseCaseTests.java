package com.sportdec.search.mashup.api.domain.usecases;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sportdec.search.mashup.api.domain.BaseTest;
import com.sportdec.search.mashup.api.domain.Repository;

public class SearchProjectsAndTweetsUseCaseTests extends BaseTest {

	@Before
	public void setup() {
		super.setup();
	}
	
	@Test
    public void when_has_a_valid_keyword_should_return_projects_and_its_tweets() {
		String keyword = "Football";
		List<Repository> repositories = factory.newSearchProjectAndTweetsUseCase(keyword).execute();
		
		Assert.assertNotNull(repositories);
		Assert.assertEquals(1, repositories.size());
    }
}