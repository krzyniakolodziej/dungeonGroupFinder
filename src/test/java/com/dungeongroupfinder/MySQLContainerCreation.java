package com.dungeongroupfinder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategyTarget;

import java.time.Duration;

public class MySQLContainerCreation {
    public static Integer containerPort;

    @Rule
    public GenericContainer MySQLContainer = new GenericContainer("mysql:8.0.21")
            .waitingFor(new WaitStrategy() {
                @Override
                public void waitUntilReady(WaitStrategyTarget waitStrategyTarget) {

                }

                @Override
                public WaitStrategy withStartupTimeout(Duration startupTimeout) {
                    return null;
                }})
            .withExposedPorts(3306);

    @Before
    public void getContainerPort() {
        containerPort = MySQLContainer.getFirstMappedPort();
    }

    @Test
    public void hello() {
        System.out.println("hello "+ containerPort);
    }
}
