package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import com.dungeongroupfinder.exceptions.PlayerNotFoundException;
import com.dungeongroupfinder.services.PlayerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

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
    public void addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        Optional<Player> playerOptional = playerService.getPlayerById(id);

        if(!playerOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        player.setId(id);
        playerService.addPlayer(player);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) throws Exception {
        Optional<Player> playerOptional = playerService.getPlayerById(id);
        if(!playerOptional.isPresent()) {
            throw new PlayerNotFoundException("id: " + id);
        }
        return playerOptional.get();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updatePlayerRoleById(@PathVariable int id, @RequestBody Roles role) {
        playerService.updatePlayerRoleById(id, role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deletePlayerById(id);
    }
}
