package com.dungeongroupfinder.controllers.test;

import com.dungeongroupfinder.GuildFinderIntegrationTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class GuildControllerTest extends GuildFinderIntegrationTest {

    @Test
    public void testTestGuilds() throws Exception {
        mvc.perform(get("/guilds"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(5))
                .andExpect(jsonPath("$[0].guildName").value("TurboSzklanki"))
                .andExpect(jsonPath("$[0].ownerId").value(0))
                .andExpect(jsonPath("$[0].memberCount").value(0))
                .andExpect(jsonPath("$[0].full").value(false));
    }
}
