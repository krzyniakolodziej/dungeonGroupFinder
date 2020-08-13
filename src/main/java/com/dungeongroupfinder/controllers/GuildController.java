package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.helpers.HelperClass;
import com.dungeongroupfinder.security.PlayerDetails;
import com.dungeongroupfinder.services.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Guild createGuild(@RequestBody Guild guild, Principal principal) {
        PlayerDetails playerDetails = HelperClass.castToPlayerDetails(principal);
        if(playerDetails.getPlayer().getGuildId() != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This player has a guild already.");
        }
        return guildService.createGuild(guild, playerDetails);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGuildById(@PathVariable int id, Principal principal) {
        PlayerDetails playerDetails = HelperClass.castToPlayerDetails(principal);
        List<Guild> guildList = guildService.getGuildById(id);
        if(guildList == null || guildList.get(0) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A guild with given id doesn't exist.");
        } else if (guildList.get(0).getOwnerId() != playerDetails.getPlayer().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "You don't have permission to do that.");
        }
        guildService.deleteGuildById(id, playerDetails);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public Guild updateGuild(@PathVariable int id, @RequestBody Guild guild, Principal principal) {
        PlayerDetails playerDetails = HelperClass.castToPlayerDetails(principal);
        List<Guild> guildList = guildService.getGuildById(id);
        if(guildList == null || guildList.get(0) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A guild with given id doesn't exist.");
        } else if (guildList.get(0).getOwnerId() != playerDetails.getPlayer().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "You don't have permission to do this.");
        }
        guild.setId(id);
        guild.setOwnerId(guildList.get(0).getOwnerId());
        return guildService.createGuild(guild, playerDetails);
    }

    @GetMapping("/{id}")
    public List<Guild> getPlayerById(@PathVariable int id) {
        List<Guild> foundPlayerList = guildService.getGuildById(id);
        if(foundPlayerList == null || foundPlayerList.get(0) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A guild with given id doesn't exist.");
        }
        return foundPlayerList;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/join/{guildId}")
    public void addPlayerToGuild(@PathVariable int guildId,
                                 @RequestBody int playerId, Principal principal) {
        guildService.addPlayerToGuild(guildId, playerId,
                HelperClass.castToPlayerDetails(principal));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave/{guildId}")
    public void removePlayerFromGuild(@PathVariable int guildId, @RequestBody int playerId,
                                      Principal principal) {
        guildService.removePlayerFromGuild(guildId, playerId,
                HelperClass.castToPlayerDetails(principal));
    }

}
