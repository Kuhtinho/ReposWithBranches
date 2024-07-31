package com.reposwithbranches.initial.client.impl;

import com.reposwithbranches.initial.exception.NotFoundException;
import com.reposwithbranches.initial.client.GitHubApiClient;
import com.reposwithbranches.initial.client.model.branch.Branch;
import com.reposwithbranches.initial.client.model.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubApiClientImpl implements GitHubApiClient {

    private static final String REPOS_API_URL = "https://api.github.com/users/%s/repos";

    private final RestClient restClient;

    @Override
    public List<Repository> getRepos(String userName) {
        return restClient.get()
                .uri(REPOS_API_URL.formatted(userName))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new NotFoundException("User with name: [%s] was not found".formatted(userName));
                })
                .toEntity(new ParameterizedTypeReference<List<Repository>>(){})
                .getBody();
    }

    @Override
    public List<Branch> getBranches(String branchesApiUrl) {
        return restClient.get()
                .uri(branchesApiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Branch>>(){})
                .getBody();
    }

}
