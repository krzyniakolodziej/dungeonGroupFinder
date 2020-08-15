package com.dungeongroupfinder.objects;


import com.dungeongroupfinder.entities.Player;
import com.dungeongroupfinder.enums.ErrorType;
import com.dungeongroupfinder.security.PlayerDetails;
import com.dungeongroupfinder.services.GuildService;
import com.dungeongroupfinder.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dungeongroupfinder.messages.ErrorMessages.getErrorMessage;

public class PendingList {

    private static List<PendingMember> pendingMemberList = new ArrayList<>(); // TODO change to hashmap

    public PendingList() { }

    public List<PendingMember> getPendingList() {
        return pendingMemberList;
    }

    public List<PendingMember> getPendingListForGuildId(int id) {
        return pendingMemberList.stream()
                .filter(element -> element.getPendingGuildId() == id)
                .collect(Collectors.toList());
    }

    public void addToPendingList(int guildId, PlayerDetails playerDetails) {
        pendingMemberList.add(new PendingMember(playerDetails.getPlayer(), guildId));
    }

    @Autowired
    private GuildService guildService;
    public void deleteFromPendingList(int pendingPlayerId, PlayerDetails playerDetails) {
        List<PendingMember> memberToBeRemovedList = pendingMemberList.stream()
                .filter(member -> member.getPlayer().getId() == pendingPlayerId)
                .collect(Collectors.toList());
        if (memberToBeRemovedList.get(0) == null) {
            throw new RuntimeException();
        }
        int guildToBeRemovedID = memberToBeRemovedList
                .get(0).getPendingGuildId();
        int pendingGuildOwnerID = guildService.getGuildById(guildToBeRemovedID)
                .get(0).getOwnerId();
        int currentlyLoggedPlayerID =playerDetails.getPlayer().getId();

        if(pendingPlayerId == currentlyLoggedPlayerID
                || pendingGuildOwnerID == currentlyLoggedPlayerID) {
            pendingMemberList.removeAll(memberToBeRemovedList); //only guild owner/logged players can remove from the list
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    getErrorMessage(ErrorType.NO_PERMISSION));
        }
    }
    @Autowired
    private PlayerService playerService;
    public void approveMember(int guildId, int playerId, PlayerDetails playerDetails) {
        int ownerId =  guildService.getGuildById(guildId).get(0).getOwnerId();
        if(ownerId != playerDetails.getPlayer().getId()) {
            throw new RuntimeException();
        }
        List<Player> pendingPlayerList = playerService.getPlayerById(playerId);
        pendingPlayerList.get(0).setGuildId(guildId);
        playerService.updatePlayer(pendingPlayerList.get(0));
        pendingMemberList.removeAll(pendingMemberList.stream()
                .filter(e -> e.getPlayer().getId() == playerId) // removes all player's pending requests
        .collect(Collectors.toList()));
    }
}