package com.dungeongroupfinder.entities;

import javax.persistence.*;

@Entity
@Table(name = "guilds")
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String guildName;
    private Integer ownerId;
    private boolean isFull = false;

    public Guild() {}

    public Guild(String guildName) {
        this.guildName = guildName;
    }

    public Guild(int id, String guildName) {
        this.id = id;
        this.guildName = guildName;
    }

    public Guild(int id, String guildName, Integer ownerId, boolean isFull) {
        this.id = id;
        this.guildName = guildName;
        this.ownerId = ownerId;
        this.isFull = isFull;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }
}
