package com.voting.session.service.impl;

import com.voting.session.exception.VotingAgendaDidNotStarted;
import com.voting.session.exception.VotingAgendaNotFoundException;
import com.voting.session.exception.VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime;
import com.voting.session.exception.VotingSessionNotFoundException;
import com.voting.session.model.VotingAgenda;
import com.voting.session.model.VotingSession;
import com.voting.session.repository.VotingSessionRepository;
import com.voting.session.service.VoteAgendaService;
import com.voting.session.service.VotingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository votingSessionRepository;

    private final VoteAgendaService voteAgendaService;

    @Autowired
    public VotingSessionServiceImpl(VotingSessionRepository votingSessionRepository, VoteAgendaService voteAgendaService) {

        this.votingSessionRepository = votingSessionRepository;
        this.voteAgendaService = voteAgendaService;
    }

    @Override
    public VotingSession createNewVotingSession(Long votingAgendaId) throws VotingAgendaNotFoundException, VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime {
        return votingSessionRepository.save(createVotingSessionModel(votingAgendaId));
    }

    @Override
    public VotingSession findById(Long sessionId) throws VotingSessionNotFoundException {

        Optional<VotingSession> votingSession = votingSessionRepository.findById(sessionId);

        if (votingSession.isPresent()) {
            return votingSession.get();
        }

        throw new VotingSessionNotFoundException("Vote session of the given id does not exists");

    }

    private VotingSession createVotingSessionModel(Long votingAgendaId) throws VotingAgendaNotFoundException, VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime {

        VotingSession votingSession = new VotingSession();

        votingSession.setCreatedAt(new Date());

        VotingAgenda votingAgenda = voteAgendaService.findById(votingAgendaId);

        validateVotingSessionAndVotingAgenda(votingAgenda);

        votingSession.setTtlVotingSession(votingAgenda.getVotingTime() != null ? votingAgenda.getVotingTime() : 1);

        votingSession.setVotingAgenda(votingAgenda);

        return votingSession;

    }

    private void validateVotingSessionAndVotingAgenda(VotingAgenda votingAgenda) throws VotingAgendaNotFoundException, VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime {

        Date nowDate = new Date();
        Date beginVoteDate = votingAgenda.getBeginVotingDate();

        if (nowDate.before(beginVoteDate)) {
            throw new VotingAgendaDidNotStarted(String.format("Cannot start voting session before voting agenda begin date of %s", beginVoteDate));
        }

        VotingSession votingSession = votingSessionRepository.findVotingSessionByVotingAgendaIdAndVotingAgendaDate(nowDate);

        if (votingSession != null) {
            throw new VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime("There is already a session running for this agenda, can't start a new session for the same agenda at same time");
        }
    }

}
