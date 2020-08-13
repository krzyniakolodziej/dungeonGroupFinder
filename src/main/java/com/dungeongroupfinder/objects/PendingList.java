package com.dungeongroupfinder.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PendingList {

    public static List<PendingMember> pendingMemberList = new ArrayList<>();

    PendingList() {}

    public void applyToGuild(PendingMember pendingMember) {
        pendingMemberList.add(pendingMember);
    }

    public void cancelApplyToGuild(int playerId) {
        List<PendingMember> memberToBeRemoved = pendingMemberList.stream()
                .filter(member -> member.getPlayer().getId() == playerId)
                .collect(Collectors.toList());
        if(memberToBeRemoved.get(0) == null) {
            throw new RuntimeException();
        }
        pendingMemberList.remove(memberToBeRemoved);
    }
}
