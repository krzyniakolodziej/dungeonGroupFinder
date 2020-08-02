package com.dungeongroupfinder;

import com.dungeongroupfinder.GuildFinderIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataValidityTest extends GuildFinderIntegrationTest {

    @Test
    public void checkTables() {
        List<String> expectedTableList = new ArrayList<String>();
        expectedTableList.add("guilds");
        expectedTableList.add("players");
        assertEquals(expectedTableList, playerRepository.getTables());
    }

    @Test
    public void checkTestPlayers() throws Exception {
        mvc.perform(get("/players"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Brox"))
                .andExpect(jsonPath("$[0].level").value(3))
                .andExpect(jsonPath("$[0].role").value("HEALER"))
                .andExpect(jsonPath("$[0].guildId").value(0));
    }

    @Test
    public void checkTestGuilds() throws Exception {
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
