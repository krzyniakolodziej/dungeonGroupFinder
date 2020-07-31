package com.dungeongroupfinder;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import com.dungeongroupfinder.repository.GuildRepository;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Sql(scripts = {"/players_schema.sql", "/guilds_schema.sql"})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(initializers = {GuildFinderIntegrationTests.Initializer.class})
public class GuildFinderIntegrationTests {

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
    public void checkTables() {
        List<String> expectedTableList = new ArrayList<String>();
        expectedTableList.add("guilds");
        expectedTableList.add("players");
        assertEquals(expectedTableList, playerRepository.getTables());

    }

    @Test
    public void konsti() {
       // insertUsers(); // PRECONFIGURE DATABASE TO CONTAIN COLUMNS
       // List<Player> d = playerRepository.findAll();
        //int a = d.size();
        //assertEquals(a, 6);
        System.out.println(playerRepository.getTables());
        System.out.println(playerRepository.findAll());

    }

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GuildRepository guildRepository;

    private void insertUsers() {
        playerRepository.save(new Player("TestName", 1, Roles.TANK));
    }

}