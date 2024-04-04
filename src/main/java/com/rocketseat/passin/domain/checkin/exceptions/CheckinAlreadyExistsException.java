package com.rocketseat.passin.domain.checkin.exceptions;

public class CheckinAlreadyExistsException extends RuntimeException{
    public CheckinAlreadyExistsException(String message) {
        super(message);
    }
}
