package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Guild;
import com.dungeongroupfinder.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildService {

    @Autowired
    GuildRepository guildRepository;

    public List<Guild> getGuilds() {
        return guildRepository.findAll();
    }

}
