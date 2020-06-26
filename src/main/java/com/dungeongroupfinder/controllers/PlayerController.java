package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping
    public List<Player> getPlayers(@RequestParam(value = "name", required = false) String name) {
       if (name == null || name.isEmpty()) {
           return playerService.getPlayers();
       }
        return playerService.getPlayersByName(name);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deletePlayer(@RequestBody int id) {
        playerService.deletePlayerById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteAllPlayers() {
        playerService.deleteAllPlayers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updatePlayer(@RequestBody Player player) {
        playerService.updatePlayer(player);
    }
}
