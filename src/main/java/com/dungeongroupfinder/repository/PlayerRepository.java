package com.dungeongroupfinder.repository;

import com.dungeongroupfinder.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findByName(String name);

    Player findById(int id);

}
