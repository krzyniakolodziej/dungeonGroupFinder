package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) {
        return playerRepository.findById(id);
    }

    public List<Player> getPlayersByName(String name) {
        return playerRepository.findByName(name);
    }

    public void addPlayer(Player player) {
        playerRepository.save(player);
    }

    public void deletePlayerById(int id) {
        playerRepository.deleteById(id);
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

}
