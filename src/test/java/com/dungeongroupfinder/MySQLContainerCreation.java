package com.dungeongroupfinder;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {MySQLContainerCreation.Initializer.class})
public class MySQLContainerCreation {

    @ClassRule
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.21")
            .withUsername("root")
            .withPassword("");
            //default DB name - test

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySQLContainer.getUsername(),
                    "spring.datasource.password=" + mySQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void hello() {
        System.out.println("hello");
    }

   // @BeforeAll
   // @Sql(scripts = {"", "/Users/kkolodziej/dumps/Dump20200727/dungeonGroupFinder_players.sql"})
    //public static void dupa() {
       // mySQLContainer.withInitScript("/Users/kkolodziej/dumps/Dump20200727/dungeonGroupFinder_guilds.sql");
    //}

    @Test
    public void konsti() {
       // insertUsers(); // PRECONFIGURE DATABASE TO CONTAIN COLUMNS
       // List<Player> d = playerRepository.findAll();
        //int a = d.size();
        //assertEquals(a, 6);
        mySQLContainer.withInitScript("/Users/kkolodziej/dumps/Dump20200727/dungeonGroupFinder_players.sql");
        System.out.println(playerRepository.showTables());
        System.out.println(playerRepository.findAll());

    }

    @Autowired
    PlayerRepository playerRepository;

    private void insertUsers() {
        playerRepository.save(new Player("TestName", 1, Roles.TANK));
    }

}

/*
 dziedziczyc inne testy po tej klasie zeby baza odpalala sie raz
 przeniesc .sql tutaj i zkomitowac
 dodac use database do dumpa / sprobowac @sql
* */