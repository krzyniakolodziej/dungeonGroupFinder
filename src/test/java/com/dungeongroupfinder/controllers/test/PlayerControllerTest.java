package com.dungeongroupfinder.controllers.test;

import com.dungeongroupfinder.GuildFinderIntegrationTest;
import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.dungeongroupfinder.utils.TestUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerControllerTest extends GuildFinderIntegrationTest {

    @Test
    public void testTestPlayers() throws Exception {
        mvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Brox"))
                .andExpect(jsonPath("$[0].level").value(3))
                .andExpect(jsonPath("$[0].role").value("HEALER"))
                .andExpect(jsonPath("$[0].guildId").value(0));
    }

    @Test
    public void testGetPlayerByName() throws Exception {
        mvc.perform(get("/players?name=Brox"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Brox"))
                .andExpect(jsonPath("$[0].level").value(3))
                .andExpect(jsonPath("$[0].role").value("HEALER"))
                .andExpect(jsonPath("$[0].guildId").value(0));
    }

    @Test
    public void testGetInvalidPlayerByName() throws Exception {
        mvc.perform(get("/players?name=invalid"))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testCreatePlayer() throws Exception {
        mvc.perform(post("/players")
                .content(asJsonString(new Player("Moo", 12, Roles.TANK)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());

        mvc.perform(get("/players/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Moo"));
    }

    @Test
    public void testPutPlayer() throws Exception {
        mvc.perform(post("/players")
                .content(asJsonString(new Player("Pear", 15, Roles.DAMAGE_DEALER)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(get("/players?name=Pear"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Pear"))
                .andExpect(jsonPath("$[0].level").value(15))
                .andExpect(jsonPath("$[0].role").value("DAMAGE_DEALER"))
                .andExpect(jsonPath("$[0].guildId").value(0));

        mvc.perform(put("/players/2")
            .content(asJsonString(new Player("Apple", 20, Roles.TANK)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mvc.perform(get("/players?name=Apple"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Apple"))
                .andExpect(jsonPath("$[0].level").value(20))
                .andExpect(jsonPath("$[0].role").value("TANK"))
                .andExpect(jsonPath("$[0].guildId").value(0));
    }

    @Test
    public void testGetPlayerById() throws Exception {
        mvc.perform(get("/players/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Brox"));

    }

    @Test
    public void testDeletePlayer() throws Exception {
       mvc.perform(post("/players")
                .content(asJsonString(new Player("Trolley", 1, Roles.DAMAGE_DEALER)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

       mvc.perform(get("/players/2"))
               .andExpect(status().isOk());

       mvc.perform(delete("/players/2"))
               .andExpect(status().isNoContent());

       mvc.perform(get("/players/2"))
               .andExpect(status().isNotFound());
    }


}
