package com.reposwithbranches.initial.service;

import com.reposwithbranches.initial.service.dto.RepoWithBranches;

import java.util.List;

public interface ReposWithBranchesService {

    List<RepoWithBranches> getReposWithBranches(String username);
}
