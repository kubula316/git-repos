package com.jakub.git_repos.model.response;

public record BranchDTO(
        String name,
        String lastCommitSha
) {}
