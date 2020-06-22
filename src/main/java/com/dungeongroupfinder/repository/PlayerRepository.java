package com.dungeongroupfinder.repository;

import com.dungeongroupfinder.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
