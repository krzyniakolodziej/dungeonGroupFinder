package com.dungeongroupfinder.entities;

public class Player {

    private String name;
    private int level;

    public Player(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public Player(Player player) {
        this.name = player.getName();
        this.level = player.getLevel();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
