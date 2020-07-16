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
    public void addGuild(@RequestBody Guild guild) {
        guildService.addGuild(guild);
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

}
