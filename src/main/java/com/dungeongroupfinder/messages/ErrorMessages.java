package com.dungeongroupfinder.messages;

import com.dungeongroupfinder.enums.ErrorType;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages {

    private static Map<ErrorType, String> errorMessagesMap = new HashMap<ErrorType, String>() {{
        put(ErrorType.NO_PERMISSION, "You don't have permission to perform this action.");
        put(ErrorType.PLAYER_ID_DOESNT_EXIST, "A player with given id doesn't exist.");
        put(ErrorType.GUILD_ID_DOESNT_EXIST, "A guild with given id doesn't exist.");
        put(ErrorType.PASSWORDS_DONT_MATCH, "Given passwords don't match each other");
        put(ErrorType.PLAYER_HAS_ALREADY_GUILD, "This player has a guild already.");
    }};

    public static String getErrorMessage(ErrorType messageType) {
        return errorMessagesMap.get(messageType);
    }

}
