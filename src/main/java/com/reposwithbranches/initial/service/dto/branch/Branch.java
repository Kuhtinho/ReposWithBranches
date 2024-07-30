package com.reposwithbranches.initial.service.dto.branch;

import lombok.Builder;

@Builder
public record Branch(String name, Commit commit) {
}
