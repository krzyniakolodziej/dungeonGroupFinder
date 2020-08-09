package com.dungeongroupfinder.security;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerDetailsService implements UserDetailsService {
    @Autowired
    PlayerService playerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Player> player = playerService.getPlayerByName(username);
        return new PlayerDetails(player.get(0));
    }
}
