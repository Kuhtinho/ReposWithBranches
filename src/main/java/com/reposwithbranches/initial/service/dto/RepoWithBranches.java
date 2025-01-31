package com.reposwithbranches.initial.service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RepoWithBranches(
        String repositoryName,
        String ownerLogin,
        List<BranchWithLastCommit> branches
) {
}
