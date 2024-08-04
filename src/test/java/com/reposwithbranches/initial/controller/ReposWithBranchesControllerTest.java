package com.reposwithbranches.initial.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reposwithbranches.initial.service.dto.RepoWithBranches;
import com.reposwithbranches.initial.util.TestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReposWithBranchesControllerTest {

    private static final String API = "/github-repos";
    private static final String LOCALHOST = "http://localhost:";

    private final ObjectMapper objectMapper= TestUtils.getObjectMapper();

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private Integer port;

    @Test
    public void getReposForUsers_success() {
        String expected = TestUtils.readResource("reposWithBranches.json");

        this.webTestClient
                .get()
                .uri(LOCALHOST + port + "/" + API + "?userName=Kuhtinho")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(new ParameterizedTypeReference<List<RepoWithBranches>>() {})
                .isEqualTo(mapResponse(expected));
    }

    @Test
    public void getReposForUsers_failure() {
        this.webTestClient
                .get()
                .uri("http://localhost:" + port + "/" + API + "?userName=kasdasiq")
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @SneakyThrows
    private List<RepoWithBranches> mapResponse(String response) {
        return objectMapper.readValue(response, new TypeReference<>() {});
    }

}