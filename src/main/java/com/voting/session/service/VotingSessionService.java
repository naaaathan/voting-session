package com.voting.session.service;

import com.voting.session.exception.VotingAgendaNotFoundException;
import com.voting.session.exception.VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime;
import com.voting.session.exception.VotingSessionNotFoundException;
import com.voting.session.model.VotingSession;

public interface VotingSessionService  {

    VotingSession createNewVotingSession(Long votingAgendaId) throws VotingAgendaNotFoundException, VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime;

    VotingSession findById(Long sessionId) throws VotingSessionNotFoundException;

}
