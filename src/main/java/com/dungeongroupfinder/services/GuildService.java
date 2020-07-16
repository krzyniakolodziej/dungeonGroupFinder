package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildService {

    @Autowired
    private GuildRepository guildRepository;

    public List<Guild> getGuilds() {
        return guildRepository.findAll();
    }

    public void addGuild(Guild guild) {
        guildRepository.save(guild);
    }

    public void modifyGuild(Guild guild) {
        guildRepository.save(guild);
    }

    public void deleteGuildById(int guildId) {
        guildRepository.deleteById(guildId);
    }

}
