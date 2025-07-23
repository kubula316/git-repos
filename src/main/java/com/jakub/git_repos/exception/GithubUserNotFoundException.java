package com.jakub.git_repos.exception;

public class GithubUserNotFoundException extends RuntimeException {
    public GithubUserNotFoundException(String username) {
        super("GitHub user '" + username + "' not found.");
    }
}
