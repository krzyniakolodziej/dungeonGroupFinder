package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.repository.GuildRepository;
import com.dungeongroupfinder.repository.PlayerRepository;
import com.dungeongroupfinder.security.PlayerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildService {

    @Autowired
    private GuildRepository guildRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerDetails playerDetails;

    public List<Guild> getGuilds() {
        return guildRepository.findAll();
    }

    public void createGuild(Guild guild) {
        Integer ownerId = playerDetails.getPlayer().getId();
        guild.setOwnerId(ownerId);
        guildRepository.save(guild);
    }

    public void deleteGuildById(int guildId) {
        Integer playerId = playerDetails.getPlayer().getId();
        Guild guildToBeDeleted = guildRepository.findById(guildId);
        if(playerId != guildToBeDeleted.getOwnerId()) {
            throw new AccessDeniedException("Only guild owner can delete the guild.");
        }
        guildRepository.clearGivenGuild(guildId);
        guildRepository.deleteById(guildId);
    }

    public void modifyGuild(Guild guild) {
        Integer playerId = playerDetails.getPlayer().getId();
        if(playerId != guild.getOwnerId()) {
            throw new AccessDeniedException("Only guild owner can modify the guild.");
        }
        guildRepository.save(guild);
    }

    public void addPlayerToGuild(int guildId, int playerId) {
        Player player = playerRepository.findById(playerId).get(0);
        player.setGuildId(guildId);
        playerRepository.save(player);
        Guild guild = guildRepository.findById(guildId);
        guild.addOneMember();
        guildRepository.save(guild);
    }

    public void removePlayerFromGuild(int guildId, int playerId) {
        Player player = playerRepository.findById(playerId).get(0);
        player.setGuildId(0);
        playerRepository.save(player);
        Guild guild = guildRepository.findById(guildId);
        guild.removeOneMember();
        guildRepository.save(guild);
    }

}
