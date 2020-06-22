package com.dungeongroupfinder.controllers;

/*import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getGroup();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayer(@RequestBody @NonNull Player player) {
        System.out.println(player.toString());
        playerService.addPlayer(player);
    }

    @DeleteMapping
    public void removePlayer(@RequestBody String name) {
        System.out.println(name + " removed");
        playerService.removePlayer(name);
    }

    @PutMapping
    public void updatePlayer(@RequestBody Player player) {
        playerService.updatePlayer(player);
    }

}*/
