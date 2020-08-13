package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.helpers.HelperClass;
import com.dungeongroupfinder.security.PlayerDetails;
import com.dungeongroupfinder.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if(!player.getPassword().equals(player.getMatchingPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Given passwords don't match each other");
        }
        Player playerToBeCreated = playerService.createPlayer(player,
                HelperClass.castToPlayerDetails(principal));
        return playerToBeCreated.getId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player, Principal principal) {
        PlayerDetails playerDetails = HelperClass.castToPlayerDetails(principal);
        List<Player> playerList = playerService.getPlayerById(id);
        if(playerList == null || playerList.get(0) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        } else if (playerList.get(0).getId() != playerDetails.getPlayer().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "You don't have permission to do this.");
        }
        player.setId(id);
        return playerService.createPlayer(player, playerDetails);
    }

    @GetMapping("/{id}")
    public List<Player> getPlayerById(@PathVariable int id) {
        List<Player> foundPlayerList = playerService.getPlayerById(id);
        if(foundPlayerList == null || foundPlayerList.get(0) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        }
        return foundPlayerList;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id, Principal principal) {
        PlayerDetails playerDetails = HelperClass.castToPlayerDetails(principal);
        List<Player> foundPlayerList = playerService.getPlayerById(id);
        if (foundPlayerList == null || foundPlayerList.get(0) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A player with given id doesn't exist.");
        } else if (id != playerDetails.getPlayer().getId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "You don't have permission to do this");
        }
        playerService.deletePlayerById(id, playerDetails);
    }
}
