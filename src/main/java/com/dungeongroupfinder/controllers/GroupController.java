package com.dungeongroupfinder.controllers;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.services.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupManager groupManager;

    @GetMapping("/getGroup")
    public List<Player> getGroup() {
        return groupManager.getGroup();
    }

    @PostMapping("/addPlayer")
    public boolean addPlayer(@RequestBody Player player) {
        System.out.println(player);
        return groupManager.addPlayer(player);
    }

    @DeleteMapping("/removePlayer")
    public void removePlayer(@RequestBody String name) {
        System.out.println(name + " removed");
        groupManager.removePlayer(name);
    }

}
