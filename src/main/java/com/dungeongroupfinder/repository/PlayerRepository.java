package com.dungeongroupfinder.repository;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findByName(String name);

    @Query(value = "SELECT * FROM players WHERE guild_id = :id", nativeQuery = true)
    List<Player> findByGuildId(@Param("id") int id);

    Optional<Player> findById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Player SET role = :role WHERE id = :id")
    void updatePlayerRoleById(@Param("id") int id, @Param("role") Roles role);

    @Query(value = "show tables", nativeQuery = true)
    List<String> getTables();
}
