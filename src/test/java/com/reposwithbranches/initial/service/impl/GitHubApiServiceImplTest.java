package com.reposwithbranches.initial.service.impl;

import com.reposwithbranches.initial.exception.NotFoundException;
import com.reposwithbranches.initial.service.GitHubApiService;
import com.reposwithbranches.initial.service.dto.branch.Branch;
import com.reposwithbranches.initial.service.dto.repository.Repository;
import com.reposwithbranches.initial.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GitHubApiServiceImplTest {

    private static final String BRANCHES_URL = "branchesUrl";
    private static final String USER_NAME = "userName";
    private static final String REPOS_API_URL = "https://api.github.com/users/%s/repos";

    private MockRestServiceServer mockServer;
    private GitHubApiService gitHubApiService;

    @BeforeEach
    public void setUp() {
        RestClient.Builder builder = RestClient.builder();
        mockServer = MockRestServiceServer.bindTo(builder).build();
        gitHubApiService = new GitHubApiServiceImpl(builder.build(), TestUtils.getObjectMapper());
    }

    @Test
    public void getReposSuccess(){
        String response = TestUtils.readResource("repositories.json");

        mockServer.expect(MockRestRequestMatchers.requestTo(REPOS_API_URL.formatted(USER_NAME)))
                .andRespond(MockRestResponseCreators.withSuccess(response, MediaType.APPLICATION_JSON));

        List<Repository> actual = gitHubApiService.getRepos(USER_NAME);

        assertFalse(actual.isEmpty());
    }

    @Test
    public void getReposForNotExistingUser(){
        mockServer.expect(MockRestRequestMatchers.requestTo(REPOS_API_URL.formatted(USER_NAME)))
                .andRespond(MockRestResponseCreators.withResourceNotFound());

        assertThrows(NotFoundException.class, () -> gitHubApiService.getRepos(USER_NAME));
    }

    @Test
    public void getBranchesSuccess(){
        String response = TestUtils.readResource("branches.json");

        mockServer.expect(MockRestRequestMatchers.requestTo(BRANCHES_URL))
                .andRespond(MockRestResponseCreators.withSuccess(response, MediaType.APPLICATION_JSON));

        List<Branch> actual = gitHubApiService.getBranches(BRANCHES_URL);

        assertFalse(actual.isEmpty());
    }

}