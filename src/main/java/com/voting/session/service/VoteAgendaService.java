package com.voting.session.service;

import com.voting.session.exception.DateFormatException;
import com.voting.session.model.VotingAgenda;
import com.voting.session.view.VoteAgendaView;

import java.util.Optional;

public interface VoteAgendaService {

    public VotingAgenda save(VoteAgendaView voteAgendaView) throws DateFormatException;

    Optional<VotingAgenda> findById(Long id);

    VotingAgenda findVotingAgendaTitleByVotingSessionId(Long sessionId);
}
