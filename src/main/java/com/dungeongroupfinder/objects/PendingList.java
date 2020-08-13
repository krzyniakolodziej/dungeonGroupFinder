package com.dungeongroupfinder.objects;


import com.dungeongroupfinder.security.PlayerDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PendingList {

    private static List<PendingMember> pendingMemberList = new ArrayList<>();

    public PendingList() {}

    public List<PendingMember> getPendingList() {
        return pendingMemberList;
    }

    public void addToPendingList(int guildId, PlayerDetails playerDetails) {
        pendingMemberList.add(new PendingMember(playerDetails.getPlayer(), guildId));
    }

    public void deleteFromPendingList(int playerId) {
        try {
            PendingMember memberToBeRemoved = pendingMemberList.stream()
                    .filter(member -> member.getPlayer().getId() == playerId)
                    .collect(Collectors.toList()).get(0);
            pendingMemberList.remove(memberToBeRemoved);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
