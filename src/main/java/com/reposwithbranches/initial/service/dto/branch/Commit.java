package com.reposwithbranches.initial.service.dto.branch;

import lombok.Builder;

@Builder
public record Commit(String sha) {
}
