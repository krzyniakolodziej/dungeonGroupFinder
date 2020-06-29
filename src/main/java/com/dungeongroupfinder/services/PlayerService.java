package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

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

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    /*public void updateRoleById(int id, Roles role) {
        Player player = playerRepository.findById(id);
        player.setRole(role);
        playerRepository.save(player);
    }*/ // not optimal approach, the proper one is presented below

    public void updatePlayerRoleById(int id, Roles role) {
        playerRepository.updatePlayerRoleById(id, role);
    }

}
// try to construct webhooks - on update/post event in DB we check if there is a full group,
// full groups are somehow separated from the rest