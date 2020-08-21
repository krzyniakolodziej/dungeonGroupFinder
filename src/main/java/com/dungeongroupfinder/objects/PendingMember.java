package com.dungeongroupfinder.objects;

import com.dungeongroupfinder.entities.Player;

public class PendingMember {
    private Player player;
    private int pendingGuildId;

    public PendingMember(Player player, int pendingGuildId) {
        this.player = player;
        this.pendingGuildId = pendingGuildId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPendingGuildId() {
        return pendingGuildId;
    }

    public void setPendingGuildId(int pendingGuildId) {
        this.pendingGuildId = pendingGuildId;
    }
}
