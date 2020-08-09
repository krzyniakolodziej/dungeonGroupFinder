package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.services.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guilds")
public class GuildController {

    @Autowired
    private GuildService guildService;

    @GetMapping
    public List<Guild> getGuilds() {
        return guildService.getGuilds();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createGuild(@RequestBody Guild guild) {
        guildService.createGuild(guild);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteGuildById(@RequestBody int id) {
        guildService.deleteGuildById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void modifyGuild(@RequestBody Guild guild) {
        guildService.modifyGuild(guild);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/join/{guildId}")
    public void addPlayerToGuild(@PathVariable int guildId, @RequestBody int playerId) {
        guildService.addPlayerToGuild(guildId, playerId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave/{guildId}")
    public void removePlayerFromGuild(@PathVariable int guildId, @RequestBody int playerId) {
        guildService.removePlayerFromGuild(guildId, playerId);
    }

}
