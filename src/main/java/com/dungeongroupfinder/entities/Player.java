package com.dungeongroupfinder.entities;

import com.dungeongroupfinder.enums.Roles;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int level;
    private Roles role;
    private Integer guildId;

    protected Player() {}

    public Player(String name, int level, Roles role, Integer guildId) {
        this.name = name;
        this.level = level;
        this.role = role;
        this.guildId = guildId;
    }

    public Player(int id, String name, int level, Roles role, Integer guildId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.role = role;
        this.guildId = guildId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", role=" + role +
                ", guildId=" + guildId +
                '}';
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getGuildId() {
        return guildId;
    }

    public void setGuildId(Integer guildId) {
        this.guildId = guildId;
    }
}
