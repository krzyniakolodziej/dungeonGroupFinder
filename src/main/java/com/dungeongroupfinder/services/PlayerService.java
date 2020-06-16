package com.dungeongroupfinder.services;

import com.dungeongroupfinder.entities.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private List<Player> group; // change to hashmap

    public PlayerService() {
        this.group = new ArrayList<>();
        group.add(new Player("player1", 1));
        group.add(new Player("player2", 2));
    }

    public List<Player> getGroup() {
        return group;
    }

    public void setGroup(List<Player> group) {
        this.group = group;
    }

    public boolean addPlayer(Player player) {
        System.out.println(player);
        return this.group.add(new Player(player));
    }

    public void removePlayer(String name) {
        group = group.stream()
                .filter(group -> !name.equals(group.getName()))
                .collect(Collectors.toList());
    }

    public void updatePlayer(Player player) {
        // todo update player
    }

}
