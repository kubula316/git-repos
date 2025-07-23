package com.jakub.git_repos.service;



import com.jakub.git_repos.model.response.RepositoryDTO;

import java.util.List;

public interface GithubService {
    List<RepositoryDTO> getUserRepositories(String username);
}
