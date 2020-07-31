package com.dungeongroupfinder.controllers.test;

import com.dungeongroupfinder.GuildFinderIntegrationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class PlayerControllerTest extends GuildFinderIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void chuj() throws Exception {
        mvc.perform(get("/players"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(6))
                .andExpect(jsonPath("$[0].id").value(2));
    }
}
