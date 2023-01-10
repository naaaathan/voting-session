package com.voting.session.exception;

public class VotingSessionNotFoundException extends RuntimeException {
    public VotingSessionNotFoundException(String message) {
        super(message);
    }
}
