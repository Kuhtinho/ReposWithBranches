package com.reposwithbranches.initial.client.model.branch;

import lombok.Builder;

@Builder
public record Commit(String sha) {
}
