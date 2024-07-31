package com.reposwithbranches.initial.service.impl;

import com.reposwithbranches.initial.client.impl.GitHubApiClientImpl;
import com.reposwithbranches.initial.exception.NotFoundException;
import com.reposwithbranches.initial.client.GitHubApiClient;
import com.reposwithbranches.initial.client.model.branch.Branch;
import com.reposwithbranches.initial.client.model.repository.Repository;
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
public class GitHubApiClientImplTest {

    private static final String BRANCHES_URL = "branchesUrl";
    private static final String USER_NAME = "userName";
    private static final String REPOS_API_URL = "https://api.github.com/users/%s/repos";

    private MockRestServiceServer mockServer;
    private GitHubApiClient gitHubApiClient;

    @BeforeEach
    public void setUp() {
        RestClient.Builder builder = RestClient.builder();
        mockServer = MockRestServiceServer.bindTo(builder).build();
        gitHubApiClient = new GitHubApiClientImpl(builder.build());
    }

    @Test
    public void getReposSuccess(){
        String response = TestUtils.readResource("repositories.json");

        mockServer.expect(MockRestRequestMatchers.requestTo(REPOS_API_URL.formatted(USER_NAME)))
                .andRespond(MockRestResponseCreators.withSuccess(response, MediaType.APPLICATION_JSON));

        List<Repository> actual = gitHubApiClient.getRepos(USER_NAME);

        assertFalse(actual.isEmpty());
    }

    @Test
    public void getReposForNotExistingUser(){
        mockServer.expect(MockRestRequestMatchers.requestTo(REPOS_API_URL.formatted(USER_NAME)))
                .andRespond(MockRestResponseCreators.withResourceNotFound());

        assertThrows(NotFoundException.class, () -> gitHubApiClient.getRepos(USER_NAME));
    }

    @Test
    public void getBranchesSuccess(){
        String response = TestUtils.readResource("branches.json");

        mockServer.expect(MockRestRequestMatchers.requestTo(BRANCHES_URL))
                .andRespond(MockRestResponseCreators.withSuccess(response, MediaType.APPLICATION_JSON));

        List<Branch> actual = gitHubApiClient.getBranches(BRANCHES_URL);

        assertFalse(actual.isEmpty());
    }

}