package com.voting.session.exception;

public class VotingSessionNotEndedException extends RuntimeException {
    public VotingSessionNotEndedException(String message) {
        super(message);
    }
}
