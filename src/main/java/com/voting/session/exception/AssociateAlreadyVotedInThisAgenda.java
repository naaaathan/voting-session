package com.voting.session.exception;

public class AssociateAlreadyVotedInThisAgenda extends RuntimeException {
    public AssociateAlreadyVotedInThisAgenda(String message) {
        super(message);
    }
}
