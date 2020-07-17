package com.dungeongroupfinder.repository;

import com.dungeongroupfinder.entities.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Integer> {

    Guild findById(int id);
}
