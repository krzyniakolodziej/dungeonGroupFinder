package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayerById(int id) {
        return playerRepository.findById(id);
    }

    public List<Player> getPlayerByName(String name) {
        return playerRepository.findByName(name);
    }

    public Player createPlayer(Player player) {
        return playerRepository.saveAndFlush(player);
    }

    public void deletePlayerById(int id) {
        playerRepository.deleteById(id);
    }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

}