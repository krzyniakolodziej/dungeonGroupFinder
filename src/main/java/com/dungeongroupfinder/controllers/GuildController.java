package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.services.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guilds")
public class GuildController {

    @Autowired
    GuildService guildService;

    @GetMapping
    public List<Guild> getGuilds() {
        return guildService.getGuilds();
    }

}
