package com.jakub.git_repos.service;

import com.jakub.git_repos.exception.GithubUserNotFoundException;
import com.jakub.git_repos.model.GitHubBranchResponseDTO;
import com.jakub.git_repos.model.GitHubRepoResponseDTO;
import com.jakub.git_repos.model.response.BranchDTO;
import com.jakub.git_repos.model.response.RepositoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

    private final RestTemplate restTemplate;
    private final String GITHUB_API = "https://api.github.com";

    public List<RepositoryDTO> getUserRepositories(String username) {
        String reposUrl = GITHUB_API + "/users/" + username + "/repos";

        try {
            ResponseEntity<GitHubRepoResponseDTO[]> response = restTemplate.getForEntity(reposUrl, GitHubRepoResponseDTO[].class);

            GitHubRepoResponseDTO[] repos = response.getBody();

            if (repos == null) return List.of();

            return Arrays.stream(repos)
                    .filter(repo -> !repo.isFork())
                    .map(repo -> {
                        List<BranchDTO> branches = getBranches(username, repo.getName());
                        return new RepositoryDTO(
                                repo.getName(),
                                repo.getOwner().getLogin(),
                                branches
                        );
                    })
                    .toList();

        } catch (HttpClientErrorException.NotFound e) {
            throw new GithubUserNotFoundException(username);
        }
    }


    private List<BranchDTO> getBranches(String owner, String repoName) {
        String branchesUrl = GITHUB_API + "/repos/" + owner + "/" + repoName + "/branches";
        ResponseEntity<GitHubBranchResponseDTO[]> response = restTemplate.getForEntity(branchesUrl, GitHubBranchResponseDTO[].class);

        GitHubBranchResponseDTO[] branches = response.getBody();
        if (branches == null) return List.of();

        return Arrays.stream(branches)
                .map(branch -> new BranchDTO(branch.getName(), branch.getCommit().getSha()))
                .toList();
    }
}
