package com.dungeongroupfinder.repository;

import com.dungeongroupfinder.entities.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GuildRepository extends JpaRepository<Guild, Integer> {

    List<Guild> findById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE players SET guild_id = 0 WHERE guild_id = :id", nativeQuery = true)
    void clearGivenGuild(@Param("id") int id);
}
