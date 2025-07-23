package com.jakub.git_repos.model.response;

import java.util.List;

public record RepositoryDTO(
        String name,
        String ownerLogin,
        List<BranchDTO> branches
) {}
