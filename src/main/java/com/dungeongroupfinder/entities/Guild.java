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
    private Integer memberCount;
    private boolean isFull = false;

    public Guild() {}

    public Guild(String guildName) {
        this.guildName = guildName;
        this.memberCount = 0;
    }

    public Guild(int id, String guildName) {
        this.id = id;
        this.guildName = guildName;
        this.memberCount = 1;
        this.isFull = false;
    }

    public Guild(int id, String guildName, Integer ownerId) {
        this.id = id;
        this.guildName = guildName;
        this.ownerId = ownerId;
        this.isFull = false;
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

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public void addOneMember() {
        this.memberCount += 1;
        if (this.memberCount == 10) {
            isFull = true;
        }
    }

    public void removeOneMember() {
        this.memberCount -= 1;
        if (this.isFull && this.memberCount < 10) {
            this.isFull = false;
        }
    }
}
