package com.reposwithbranches.initial.controller;

import com.reposwithbranches.initial.service.ReposWithBranchesService;
import com.reposwithbranches.initial.service.dto.RepoWithBranches;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github_repos")
@RequiredArgsConstructor
public class ReposWithBranchesController {

    private final ReposWithBranchesService reposWithBranchesService;

    @GetMapping("/{userName}")
    public List<RepoWithBranches> getReposForUser(@PathVariable String userName) {
        return reposWithBranchesService.getReposWithBranches(userName);
    }
}
