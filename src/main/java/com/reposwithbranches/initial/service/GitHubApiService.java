package com.reposwithbranches.initial.service;

import com.reposwithbranches.initial.service.dto.branch.Branch;
import com.reposwithbranches.initial.service.dto.repository.Repository;

import java.util.List;

public interface GitHubApiService {
    List<Repository> getRepos(String userName);

    List<Branch> getBranches(String repositoryName);
}
