package com.reposwithbranches.initial.client.model.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Repository(
        String name,
        Owner owner,
        @JsonProperty("fork")
        Boolean isFork,
        @JsonProperty("branches_url")
        String branchUrl
) {
}
