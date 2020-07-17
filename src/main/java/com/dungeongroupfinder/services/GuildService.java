package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.repository.GuildRepository;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addGuild(Guild guild) {
        guildRepository.save(guild);
    }

    public void deleteGuildById(int guildId) {
        guildRepository.deleteById(guildId);
    }

    public void modifyGuild(Guild guild) {
        guildRepository.save(guild);
    }

    public void addPlayerToGuild(int guildId, int playerId) {
        Player player = playerRepository.findById(playerId);
        player.setGuildId(guildId);
        playerRepository.save(player);
        Guild guild = guildRepository.findById(guildId);
        guild.addOneMember();
        guildRepository.save(guild);
    }

}
