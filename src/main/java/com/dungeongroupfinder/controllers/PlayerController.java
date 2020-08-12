package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.security.PlayerDetails;
import com.dungeongroupfinder.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Valid
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
        return playerService.getPlayerByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Integer createPlayer(@RequestBody Player player, Principal principal) {
        if(player.getPassword().equals(player.getMatchingPassword())) {
            Player playerToBeCreated = playerService.createPlayer(player,
                    (PlayerDetails) ((Authentication) principal).getPrincipal());
            return playerToBeCreated.getId();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given passwords doesn't match");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player, Principal principal) {
        Player foundPlayer = playerService.getPlayerById(id).get(0);
        if(foundPlayer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        }
        player.setId(id);
        return playerService.createPlayer(player,
                (PlayerDetails) ((Authentication) principal).getPrincipal());
    }

    @GetMapping("/{id}")
    public List<Player> getPlayerById(@PathVariable int id) {
        List<Player> foundPlayer = playerService.getPlayerById(id);
        if(foundPlayer == null || foundPlayer.get(0) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        }
        return foundPlayer;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id, Principal principal) {
        if(playerService.getPlayerById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        }
        playerService.deletePlayerById(id,
                (PlayerDetails) ((Authentication) principal).getPrincipal());
    }
}
