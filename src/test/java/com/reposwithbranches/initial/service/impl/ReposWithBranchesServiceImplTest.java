package com.reposwithbranches.initial.service.impl;

import com.reposwithbranches.initial.service.GitHubApiService;
import com.reposwithbranches.initial.service.dto.RepoWithBranches;
import com.reposwithbranches.initial.service.dto.branch.Branch;
import com.reposwithbranches.initial.service.dto.branch.BranchWithLastCommit;
import com.reposwithbranches.initial.service.dto.branch.Commit;
import com.reposwithbranches.initial.service.dto.repository.Owner;
import com.reposwithbranches.initial.service.dto.repository.Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReposWithBranchesServiceImplTest {

    private static final String USER_NAME = "userName";

    private static final List<Repository> REPOSITORIES = List.of(
            Repository.builder()
                    .name("repo")
                    .owner(Owner.builder().login("owner").build())
                    .branchUrl("branchUrl")
                    .build()
    );

    private static final List<Branch> BRANCHES = List.of(
            Branch.builder()
                    .name("branch")
                    .commit(Commit.builder().sha("sha").build())
                    .build()
    );

    @Mock
    private GitHubApiService gitHubApiService;

    @InjectMocks
    private ReposWithBranchesServiceImpl reposWithBranchesService;

    @Test
    public void getReposWithBranches() {
        List<RepoWithBranches> expected = List.of(
                RepoWithBranches.builder()
                        .repositoryName(REPOSITORIES.getFirst().name())
                        .ownerLogin(REPOSITORIES.getFirst().owner().login())
                        .branches(List.of(
                                BranchWithLastCommit.builder()
                                        .name(BRANCHES.getFirst().name())
                                        .lastCommitSha(BRANCHES.getFirst().commit().sha())
                                        .build())
                        )
                        .build());

        when(gitHubApiService.getRepos(anyString())).thenReturn(REPOSITORIES);
        when(gitHubApiService.getBranches(anyString())).thenReturn(BRANCHES);

        List<RepoWithBranches> actual = reposWithBranchesService.getReposWithBranches(USER_NAME);

        assertEquals(actual, expected);
    }

}