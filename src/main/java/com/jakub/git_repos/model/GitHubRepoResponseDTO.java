package com.jakub.git_repos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GitHubRepoResponseDTO {
    private String name;
    private boolean fork;
    private Owner owner;

    @Data
    public static class Owner {
        private String login;
    }
}
