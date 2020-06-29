package com.dungeongroupfinder.repository;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findByName(String name);

    Player findById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Player SET role = :role WHERE id = :id")
    void updatePlayerRoleById(@Param("id") int id, @Param("role") Roles role);
}
