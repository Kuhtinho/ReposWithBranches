package com.reposwithbranches.initial.service.dto;

import lombok.Builder;

@Builder
public record BranchWithLastCommit(String name, String lastCommitSha) {
}
