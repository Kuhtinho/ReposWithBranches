package com.reposwithbranches.initial.service.impl;

import com.reposwithbranches.initial.exception.NotFoundException;
import com.reposwithbranches.initial.service.GitHubApiService;
import com.reposwithbranches.initial.service.dto.branch.Branch;
import com.reposwithbranches.initial.service.dto.repository.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubApiServiceImpl implements GitHubApiService {

    private static final String REPOS_API_URL = "https://api.github.com/users/%s/repos";

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<Repository> getRepos(String userName) {
        return restClient.get()
                .uri(REPOS_API_URL.formatted(userName))
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> {
                    if (!response.getStatusCode().is4xxClientError()) {
                        return mapToRepos(new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8));
                    }
                    throw new NotFoundException("User with name: [%s] was not found".formatted(userName));
                });
    }

    @Override
    public List<Branch> getBranches(String branchesApiUrl) {
        String branches = restClient
                .get()
                .uri(branchesApiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);

        return mapToBranches(branches);
    }

    @SneakyThrows
    private List<Repository> mapToRepos(String response) {
        return objectMapper.readValue(response, new TypeReference<>() {
        });
    }

    @SneakyThrows
    private List<Branch> mapToBranches(String response) {
        return objectMapper.readValue(response, new TypeReference<>() {
        });
    }
}
