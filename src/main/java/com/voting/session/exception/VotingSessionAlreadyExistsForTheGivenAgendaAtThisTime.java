package com.voting.session.exception;

public class VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime extends RuntimeException {
    public VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime(String message) {
        super(message);
    }
}
