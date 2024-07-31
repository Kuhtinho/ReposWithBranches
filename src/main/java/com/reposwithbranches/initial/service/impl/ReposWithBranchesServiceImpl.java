package com.reposwithbranches.initial.service.impl;

import com.reposwithbranches.initial.client.GitHubApiClient;
import com.reposwithbranches.initial.service.ReposWithBranchesService;
import com.reposwithbranches.initial.service.dto.RepoWithBranches;
import com.reposwithbranches.initial.service.dto.BranchWithLastCommit;
import com.reposwithbranches.initial.client.model.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReposWithBranchesServiceImpl implements ReposWithBranchesService {

    private final GitHubApiClient gitHubApiClient;

    @Override
    public List<RepoWithBranches> getReposWithBranches(String userName) {
        List<RepoWithBranches> reposWithBranches;
        List<Repository> repositories = gitHubApiClient.getRepos(userName);
        reposWithBranches = repositories.stream()
                .map(repository -> RepoWithBranches.builder()
                        .repositoryName(repository.name())
                        .ownerLogin(repository.owner().login())
                        .branches(getBranchesWithLastCommits(repository.branchUrl()))
                        .build())
                .toList();
        return reposWithBranches;
    }

    private List<BranchWithLastCommit> getBranchesWithLastCommits(String branchesApiUrl) {
        return gitHubApiClient.getBranches(branchesApiUrl.replace("{/branch}", ""))
                .stream()
                .map(branch -> BranchWithLastCommit.builder()
                        .name(branch.name())
                        .lastCommitSha(branch.commit().sha())
                        .build()
                )
                .toList();
    }

}