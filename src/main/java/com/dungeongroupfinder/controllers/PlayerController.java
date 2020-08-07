package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import com.dungeongroupfinder.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getPlayers(@RequestParam(value = "name", required = false) String name) {
       if (name == null || name.isEmpty()) {
           return playerService.getPlayers();
       }
        return playerService.getPlayersByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
        Player foundPlayer = playerService.getPlayerById(id);
        if(foundPlayer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        }
        player.setId(id);
        return playerService.createPlayer(player);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        Player foundPlayer = playerService.getPlayerById(id);
        if(foundPlayer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        }
        return foundPlayer;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        if(playerService.getPlayerById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        }
        playerService.deletePlayerById(id);
    }
}
