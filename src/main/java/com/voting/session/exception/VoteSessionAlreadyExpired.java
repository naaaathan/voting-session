package com.voting.session.exception;

public class VoteSessionAlreadyExpired extends RuntimeException {
    public VoteSessionAlreadyExpired(String message) {
        super(message);
    }
}
