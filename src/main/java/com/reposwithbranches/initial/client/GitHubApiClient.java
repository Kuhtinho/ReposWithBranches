package com.reposwithbranches.initial.client;

import com.reposwithbranches.initial.client.model.branch.Branch;
import com.reposwithbranches.initial.client.model.repository.Repository;

import java.util.List;

public interface GitHubApiClient {

    List<Repository> getRepos(String userName);

    List<Branch> getBranches(String repositoryName);

}
