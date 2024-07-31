package com.reposwithbranches.initial.client.model.branch;

import lombok.Builder;

@Builder
public record Branch(String name, Commit commit) {
}
