package com.voting.session.service.impl;

import com.voting.session.exception.*;
import com.voting.session.model.Associate;
import com.voting.session.model.Vote;
import com.voting.session.model.VotingAgenda;
import com.voting.session.model.VotingSession;
import com.voting.session.repository.VoteRepository;
import com.voting.session.service.AssociateService;
import com.voting.session.service.VoteAgendaService;
import com.voting.session.service.VoteService;
import com.voting.session.service.VotingSessionService;
import com.voting.session.view.VoteCountResponseView;
import com.voting.session.view.VoteResponseView;
import com.voting.session.view.VoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class VoteServiceImpl implements VoteService {

    private final VotingSessionService votingSessionService;

    private final VoteAgendaService voteAgendaService;

    private final AssociateService associateService;

    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VotingSessionService votingSessionService, AssociateService associateService, VoteRepository voteRepository, VoteAgendaService voteAgendaService) {
        this.votingSessionService = votingSessionService;
        this.associateService = associateService;
        this.voteRepository = voteRepository;
        this.voteAgendaService = voteAgendaService;
    }

    @Override
    public VoteResponseView internilizeVote(Long sessionId, VoteView voteView) {

        VotingSession votingSession = votingSessionService.findById(sessionId);
        Associate associate = associateService.findById(voteView.getAssociateId());

        checkIfVoteIsUnderVotingSessionTimeToLive(votingSession);
        checkIfAssociatedAlreadyVoted(associate, votingSession);

        Vote vote = new Vote();

        vote.setVotingSession(votingSession);
        vote.setAssociate(associate);
        vote.setVoteChoice(voteView.getVote());

        return createVoteResponseView(save(vote));

    }


    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public VoteCountResponseView countVotes(Long sessionId) {
        Long votesYes = voteRepository.countVotesByVoteChoiceAndVotingSessionId(true, sessionId);
        Long votesNo = voteRepository.countVotesByVoteChoiceAndVotingSessionId(false, sessionId);

        checkIfTheSessionOfVotingAlreadyFinished(sessionId);

        return fillVoteCountResponseView(sessionId, votesYes, votesNo);

    }

    private void checkIfTheSessionOfVotingAlreadyFinished(Long sessionId) {

        VotingSession votingSession = votingSessionService.findById(sessionId);

        try {
            checkIfVoteIsUnderVotingSessionTimeToLive(votingSession);
        } catch (VoteSessionAlreadyExpired e) {
            return;
        }

        throw new VotingSessionNotEndedException("The session is still up to votes, can't count votes until it finishes.");

    }

    private VoteCountResponseView fillVoteCountResponseView(Long sessionId, Long votesYes, Long votesNo) {
        VoteCountResponseView voteCountResponseView = new VoteCountResponseView();
        voteCountResponseView.setVotesYes(votesYes);
        voteCountResponseView.setVotesNo(votesNo);

        VotingAgenda votingAgenda = voteAgendaService.findVotingAgendaTitleByVotingSessionId(sessionId);
        voteCountResponseView.setVotingAgendaId(votingAgenda.getId());
        voteCountResponseView.setVoteAgendaName(votingAgenda.getVotingTitle());
        voteCountResponseView.setVoteSessionId(sessionId);
        return voteCountResponseView;
    }


    private void checkIfAssociatedAlreadyVoted(Associate associate, VotingSession voteSession) {

        Vote vote = voteRepository.findByAssociateAndVotingSessionVotingAgenda(associate, voteSession.getVotingAgenda());

        if (vote != null) {
            throw new AssociateAlreadyVotedInThisAgenda("Can not vote in an agend more than one time.");
        }
    }


    private void checkIfVoteIsUnderVotingSessionTimeToLive(VotingSession votingSession) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(votingSession.getCreatedAt());
        calendar.add(Calendar.MINUTE, Math.toIntExact(votingSession.getTtlVotingSession()));
        Date limitDate = calendar.getTime();

        Date actualDate = new Date();

        if (actualDate.after(limitDate)) {
            throw new VoteSessionAlreadyExpired("The time for this session vote expired.");
        }
    }


    private VoteResponseView createVoteResponseView(Vote save) {

        return new VoteResponseView(save.getId(), save.getAssociate().getId(), save.getVotingSession().getId());
    }


}
