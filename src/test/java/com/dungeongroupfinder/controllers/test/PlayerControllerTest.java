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
    public void testAddPlayer() throws Exception {
        mvc.perform(post("/players")
                .content(asJsonString(new Player("Moo", 12, Roles.TANK)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());

        mvc.perform(get("/players?name=Moo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0]id").value(2))
                .andExpect(jsonPath("$[0]name").value("Moo"))
                .andExpect(jsonPath("$[0].level").value(12))
                .andExpect(jsonPath("$[0].role").value("TANK"))
                .andExpect(jsonPath("$[0].guildId").value(0));
    }

    @Test
    public void testPutPlayer() throws Exception {
        mvc.perform(put("/players/1")
            .content(asJsonString(new Player("Brox", 20, Roles.TANK)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mvc.perform(get("/players?name=Brox"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Brox"))
                .andExpect(jsonPath("$[0].level").value(20))
                .andExpect(jsonPath("$[0].role").value("TANK"))
                .andExpect(jsonPath("$[0].guildId").value(0));
    }

/*
*     @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updatePlayer(@RequestBody Player player) {
        playerService.updatePlayer(player);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updatePlayerRoleById(@PathVariable int id, @RequestBody Roles role) {
        playerService.updatePlayerRoleById(id, role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deletePlayerById(id);
    }
* */

}
