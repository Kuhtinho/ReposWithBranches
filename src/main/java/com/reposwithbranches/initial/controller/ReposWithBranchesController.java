package com.reposwithbranches.initial.controller;

import com.reposwithbranches.initial.service.ReposWithBranchesService;
import com.reposwithbranches.initial.service.dto.RepoWithBranches;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github-repos")
@RequiredArgsConstructor
public class ReposWithBranchesController {

    private final ReposWithBranchesService reposWithBranchesService;

    @GetMapping
    public List<RepoWithBranches> getReposForUser(@RequestParam String userName) {
        return reposWithBranchesService.getReposWithBranches(userName);
    }
}
