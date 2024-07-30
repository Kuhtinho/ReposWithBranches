package com.reposwithbranches.initial.service.impl;

import com.reposwithbranches.initial.service.GitHubApiService;
import com.reposwithbranches.initial.service.ReposWithBranchesService;
import com.reposwithbranches.initial.service.dto.RepoWithBranches;
import com.reposwithbranches.initial.service.dto.branch.BranchWithLastCommit;
import com.reposwithbranches.initial.service.dto.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReposWithBranchesServiceImpl implements ReposWithBranchesService {

    private final GitHubApiService gitHubApiService;

    @Override
    public List<RepoWithBranches> getReposWithBranches(String userName) {
        List<RepoWithBranches> reposWithBranches;
        List<Repository> repositories = gitHubApiService.getRepos(userName);
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
        return gitHubApiService.getBranches(branchesApiUrl.replace("{/branch}", ""))
                .stream()
                .map(branch -> BranchWithLastCommit.builder()
                        .name(branch.name())
                        .lastCommitSha(branch.commit().sha())
                        .build()
                )
                .collect(Collectors.toList());
    }

}