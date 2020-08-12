package com.dungeongroupfinder.controllers.test;

import com.dungeongroupfinder.GuildFinderIntegrationTest;
import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Brox"))
                .andExpect(jsonPath("$[0].level").value(3))
                .andExpect(jsonPath("$[0].role").value("HEALER"))
                .andExpect(jsonPath("$[0].guildId").value(2));
    }

    @Test
    public void testGetPlayerByName() throws Exception {
        mvc.perform(get("/players?name=Brox"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Brox"))
                .andExpect(jsonPath("$[0].level").value(3))
                .andExpect(jsonPath("$[0].role").value("HEALER"))
                .andExpect(jsonPath("$[0].guildId").value(2));
    }

    @Test
    public void testGetInvalidPlayerByName() throws Exception {
        mvc.perform(get("/players?name=invalid"))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testCreatePlayer() throws Exception {
        MvcResult response = mvc.perform(post("/players")
                .content(asJsonString(new Player("Moo", 12, Roles.TANK, "password")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                .andReturn();

        String id = response.getResponse().getContentAsString();

        mvc.perform(get(String.format("/players/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Moo"));
    }

    @Test
    public void testPutPlayer() throws Exception {
        MvcResult response = mvc.perform(post("/players")
                .content(asJsonString(new Player("Pear", 15,
                        Roles.DAMAGE_DEALER, "password")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String id = response.getResponse().getContentAsString();

        mvc.perform(put(String.format("/players/%s", id))
            .content(asJsonString(new Player("Apple", 20,
                    Roles.TANK, "otherpassword")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mvc.perform(get(String.format("/players/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value("Apple"))
                .andExpect(jsonPath("$[0].level").value(20))
                .andExpect(jsonPath("$[0].role").value("TANK"))
                .andExpect(jsonPath("$[0].password").value("otherpassword"));
    }

    @Test
    public void testGetPlayerById() throws Exception {
        mvc.perform(get("/players/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Brox"));

    }

    @Test
    public void testDeletePlayer() throws Exception {
       MvcResult response = mvc.perform(post("/players")
                .content(asJsonString(new Player("Banana", 1,
                        Roles.DAMAGE_DEALER, "password")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
               .andReturn();

       String id = response.getResponse().getContentAsString();

       mvc.perform(get(String.format("/players/%s", id)))
               .andExpect(status().isOk());

       mvc.perform(delete(String.format("/players/%s", id)))
               .andExpect(status().isNoContent());

       mvc.perform(get(String.format("/players/%s", id)))
               .andExpect(status().isNotFound());
    }


}
