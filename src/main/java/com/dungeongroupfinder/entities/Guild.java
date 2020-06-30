package com.dungeongroupfinder.entities;

import javax.persistence.*;

@Entity
@Table(name = "guilds")
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "guild_name")
    private String guildName;

    public Guild() {}

    public Guild(String guildName) {
        this.guildName = guildName;
    }

    public Guild(int id, String guildName) {
        this.id = id;
        this.guildName = guildName;
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
}
