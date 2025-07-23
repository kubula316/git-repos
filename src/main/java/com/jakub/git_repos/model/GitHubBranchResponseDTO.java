package com.jakub.git_repos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GitHubBranchResponseDTO {
    private String name;
    private Commit commit;

    @Data
    public static class Commit {
        private String sha;
    }
}
