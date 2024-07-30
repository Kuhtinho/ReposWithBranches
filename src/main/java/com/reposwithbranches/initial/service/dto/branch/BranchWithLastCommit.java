package com.reposwithbranches.initial.service.dto.branch;

import lombok.Builder;

@Builder
public record BranchWithLastCommit(String name, String lastCommitSha) {
}
