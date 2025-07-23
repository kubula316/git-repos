package com.jakub.git_repos.controller;



import com.jakub.git_repos.model.response.RepositoryDTO;
import com.jakub.git_repos.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/{username}/repos")
    public ResponseEntity<List<RepositoryDTO>> getUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(githubService.getUserRepositories(username));
    }
}
