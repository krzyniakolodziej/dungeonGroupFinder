package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GuildService {

    @Autowired
    GuildRepository guildRepository;

    public List<Guild> getGuilds() {
        return guildRepository.findAll();
    }

}
