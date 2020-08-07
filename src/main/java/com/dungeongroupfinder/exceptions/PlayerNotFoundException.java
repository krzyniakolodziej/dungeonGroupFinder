package com.dungeongroupfinder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException{

    private String id;

    public PlayerNotFoundException(String id) {
        super(String.format(" not found : '%s'", id));
        this.id = id;

    }

    public String getId() {
        return this.id;
    }

}
