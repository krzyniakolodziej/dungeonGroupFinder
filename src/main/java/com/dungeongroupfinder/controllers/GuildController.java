package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.security.PlayerDetails;
import com.dungeongroupfinder.services.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public void createGuild(@RequestBody Guild guild, Principal principal) {
        guildService.createGuild(guild, (PlayerDetails) ((Authentication) principal).getPrincipal());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGuildById(@PathVariable int id, Principal principal) {
        guildService.deleteGuildById(id, (PlayerDetails) ((Authentication) principal).getPrincipal());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void modifyGuild(@RequestBody Guild guild, Principal principal) {
        guildService.modifyGuild(guild, (PlayerDetails) ((Authentication) principal).getPrincipal());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/join/{guildId}")
    public void addPlayerToGuild(@PathVariable int guildId,
                                 @RequestBody int playerId, Principal principal) {
        guildService.addPlayerToGuild(guildId, playerId,
                (PlayerDetails) ((Authentication) principal).getPrincipal());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave/{guildId}")
    public void removePlayerFromGuild(@PathVariable int guildId, @RequestBody int playerId,
                                      Principal principal) {
        guildService.removePlayerFromGuild(guildId, playerId,
                (PlayerDetails) ((Authentication) principal).getPrincipal());
    }

}
