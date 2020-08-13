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

    public List<Guild> getGuilds() {
        return guildRepository.findAll();
    }

    public Guild createGuild(Guild guild, PlayerDetails playerDetails) {
        Integer ownerId = playerDetails.getPlayer().getId();
        guild.setOwnerId(ownerId);
        return guildRepository.saveAndFlush(guild);
    }

    public void deleteGuildById(int guildId, PlayerDetails playerDetails) {
        int playerId = playerDetails.getPlayer().getId();
        List<Guild> guildToBeDeleted = guildRepository.findById(guildId);
        if(playerId != guildToBeDeleted.get(0).getOwnerId()) {
            throw new AccessDeniedException("Only guild owner can delete the guild.");
        }
        guildRepository.clearGivenGuild(guildId);
        guildRepository.deleteById(guildId);
    }

    public void modifyGuild(Guild guild, PlayerDetails playerDetails) {
        Integer playerId = playerDetails.getPlayer().getId();
        if(playerId != guild.getOwnerId()) {
            throw new AccessDeniedException("Only guild owner can modify the guild.");
        }
        guildRepository.save(guild);
    }

    public List<Guild> getGuildById(int id) {
        return guildRepository.findById(id);
    }

    public void addPlayerToGuild(int guildId, int playerId, PlayerDetails playerDetails) {
        Player player = playerRepository.findById(playerId).get(0);
        player.setGuildId(guildId);
        playerRepository.save(player);
        List<Guild> guild = guildRepository.findById(guildId);
        guild.get(0).addOneMember();
        guildRepository.save(guild.get(0));
    }

    public void removePlayerFromGuild(int guildId, int playerId, PlayerDetails playerDetails) {
        Player player = playerRepository.findById(playerId).get(0);
        player.setGuildId(0);
        playerRepository.save(player);
        List<Guild> guild = guildRepository.findById(guildId);
        guild.get(0).removeOneMember();
        guildRepository.save(guild.get(0));
    }

}
