package com.dungeongroupfinder;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DungeonGroupFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DungeonGroupFinderApplication.class, args);
	}
	// move to Repository
	@Autowired
	private PlayerRepository playerRepository;

	/*@Override
	public void run (String ...args) {
		List<Player> playerList1 = playerRepository.findAll();
		playerList1.forEach(System.out::println);

		Player Adam = getPlayer();

		playerRepository.save(Adam);

		List<Player> playerList2 = playerRepository.findAll();
		playerList2.forEach(System.out::println);

	}

	private Player getPlayer() {
		return new Player("Adam", 3);
	}*/
}
