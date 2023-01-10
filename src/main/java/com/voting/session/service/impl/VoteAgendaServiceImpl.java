package com.voting.session.service.impl;

import com.voting.session.exception.VotingAgendaNotFoundException;
import com.voting.session.model.VotingAgenda;
import com.voting.session.repository.VotingAgendaRepository;
import com.voting.session.service.VoteAgendaService;
import com.voting.session.utils.DateUtils;
import com.voting.session.view.VoteAgendaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VoteAgendaServiceImpl implements VoteAgendaService {

    private final VotingAgendaRepository votingAgendaRepository;

    @Autowired
    public VoteAgendaServiceImpl(VotingAgendaRepository votingAgendaRepository) {
        this.votingAgendaRepository = votingAgendaRepository;
    }

    @Override
    public VotingAgenda save(VoteAgendaView voteAgendaView) {

        return votingAgendaRepository.save(createVoteAgendaModel(voteAgendaView));
    }


    public VotingAgenda findById(Long id) {

        Optional<VotingAgenda> votingAgenda = votingAgendaRepository.findById(id);

        if (votingAgenda.isEmpty()) {
            throw new VotingAgendaNotFoundException(String.format("Does not exist any voting agenda for the id %s", id));
        }

        return votingAgenda.get();
    }

    public VotingAgenda findVotingAgendaTitleByVotingSessionId(Long sessionId) {

        return votingAgendaRepository.findVotingAgendaTitleByVotingSession(sessionId);

    }


    private VotingAgenda createVoteAgendaModel(VoteAgendaView voteAgendaView) {

        VotingAgenda votingAgenda = new VotingAgenda();
        votingAgenda.setCreatedAt(new Date());
        votingAgenda.setBeginVotingDate(DateUtils.formatDate(voteAgendaView.getBeginVotingDate()));
        votingAgenda.setVotingTime(voteAgendaView.getVotingTime());
        votingAgenda.setVotingTitle(voteAgendaView.getVotingTitle());

        return votingAgenda;
    }


}
