package com.dungeongroupfinder.helpers;

import com.dungeongroupfinder.security.PlayerDetails;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class HelperClass {
    public static PlayerDetails castToPlayerDetails(Principal principal) {
        return (PlayerDetails) ((Authentication) principal).getPrincipal();
    }
}
