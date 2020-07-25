package com.dungeongroupfinder;

import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.Roles;
import com.dungeongroupfinder.repository.PlayerRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {MySQLContainerCreation.Initializer.class})
public class MySQLContainerCreation {

    @ClassRule
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.21")
            .withDatabaseName("integration-tests-db")
            .withUsername("name")
            .withPassword("pass");

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

    @Test
    public void konsti() {
        insertUsers(); // PRECONFIGURE DATABASE TO CONTAIN COLUMNS
        int a = playerRepository.findAll().size();
        assertEquals(a, 1);
    }

    @Autowired
    PlayerRepository playerRepository;

    private void insertUsers() {
        playerRepository.save(new Player("TestName", 1, Roles.TANK));
    }

}