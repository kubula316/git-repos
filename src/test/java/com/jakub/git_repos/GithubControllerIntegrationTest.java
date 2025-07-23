package com.jakub.git_repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GithubControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRepositoriesWithBranches() throws Exception {

        String username = "torvalds";

        mockMvc.perform(get("/api/github/{username}/repos", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].ownerLogin").value(username))
                .andExpect(jsonPath("$[0].branches").isArray())
                .andExpect(jsonPath("$[0].branches[0].name").exists())
                .andExpect(jsonPath("$[0].branches[0].lastCommitSha").exists());
    }
}