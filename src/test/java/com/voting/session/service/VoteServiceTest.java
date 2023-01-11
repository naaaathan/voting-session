package com.voting.session.service;

import com.voting.session.exception.AssociateAlreadyVotedInThisAgenda;
import com.voting.session.exception.VoteSessionAlreadyExpired;
import com.voting.session.model.Associate;
import com.voting.session.model.Vote;
import com.voting.session.model.VotingAgenda;
import com.voting.session.model.VotingSession;
import com.voting.session.repository.VoteRepository;
import com.voting.session.service.impl.VoteAgendaServiceImpl;
import com.voting.session.service.impl.VoteServiceImpl;
import com.voting.session.view.VoteCountResponseView;
import com.voting.session.view.VoteResponseView;
import com.voting.session.view.VoteView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VoteServiceTest {

    private static VotingSessionService votingSessionService;

    private static VoteAgendaService voteAgendaService;

    private static AssociateService associateService;

    private static VoteRepository voteRepository;

    private static VoteService voteService;

    @BeforeAll
    public static void setup() {
        votingSessionService = mock(VotingSessionService.class);
        voteAgendaService = mock(VoteAgendaServiceImpl.class);
        associateService = mock(AssociateService.class);
        voteRepository = mock(VoteRepository.class);
        voteService = new VoteServiceImpl(votingSessionService, associateService, voteRepository, voteAgendaService);
    }

    @Test
    public void internilizeVote_whenVotingSessionIsNotSessionTime_shouldThrowVoteSessionAlreadyExpiredException() {

        when(votingSessionService.findById(anyLong())).thenReturn(createVoteSessionWithGivenTTL(0L));
        when(associateService.findById(anyLong())).thenReturn(new Associate());

        Assertions.assertThrows(VoteSessionAlreadyExpired.class, () ->
                voteService.internilizeVote(10L, createVoteView()), "VoteSessionExpiredException should be thrown");
    }


    @Test
    public void internilizeVote_whenAssociatedAlreadyVoted_shouldThrowAssociateAlreadyVotedInThisAgendaException() {

        Associate associate = createAssociate(10);
        VotingSession votingSession = createVoteSessionWithGivenTTL(1L);

        when(votingSessionService.findById(anyLong())).thenReturn(votingSession);
        when(associateService.findById(anyLong())).thenReturn(associate);

        when(voteRepository.findByAssociateAndVotingSessionVotingAgenda(associate, votingSession.getVotingAgenda())).thenReturn(new Vote());

        Assertions.assertThrows(AssociateAlreadyVotedInThisAgenda.class, () ->
                voteService.internilizeVote(10L, createVoteView()), "VoteSessionExpiredException should be thrown");
    }

    @Test
    public void internilizeVote_whenNoExceptionIsThrown_shouldReturnVoteView() {

        Associate associate = createAssociate(10);
        VotingSession votingSession = createVoteSessionWithGivenTTL(1L);
        VoteView voteView = createVoteView();
        VoteResponseView voteResponseView = new VoteResponseView(10L, associate.getId(), votingSession.getId());
        Vote vote = createVote(associate, votingSession);

        when(votingSessionService.findById(anyLong())).thenReturn(votingSession);
        when(associateService.findById(anyLong())).thenReturn(associate);

        when(voteRepository.findByAssociateAndVotingSessionVotingAgenda(associate, votingSession.getVotingAgenda())).thenReturn(null);
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        VoteResponseView responseView = voteService.internilizeVote(votingSession.getId(), voteView);

        Assertions.assertAll("Assert response view return",
                () -> Assertions.assertEquals(voteResponseView.getId(), responseView.getId()),
                () -> Assertions.assertEquals(voteResponseView.getAssociateId(), responseView.getAssociateId()),
                () -> Assertions.assertEquals(voteResponseView.getVottingSessionId(), responseView.getVottingSessionId())
        );

    }

    @Test
    public void countVotes_whenVotingSessionAlreadyFinished_shouldReturnVoteCountView() {

        VotingSession votingSession = new VotingSession();
        votingSession.setCreatedAt(new Date());
        votingSession.setTtlVotingSession(0L);

        VoteCountResponseView voteCountResponseView = new VoteCountResponseView();
        voteCountResponseView.setVotesYes(10L);
        voteCountResponseView.setVotesNo(10L);

        when(voteRepository.countVotesByVoteChoiceAndVotingSessionId(anyBoolean(), anyLong())).thenReturn(10L);
        when(votingSessionService.findById(anyLong())).thenReturn(votingSession);
        when(voteAgendaService.findVotingAgendaTitleByVotingSessionId(anyLong())).thenReturn(new VotingAgenda());

        VoteCountResponseView response = voteService.countVotes(10L);
        Assertions.assertAll("Assert response view return",
                () -> Assertions.assertEquals(voteCountResponseView.getVotesYes(), response.getVotesYes()),
                () -> Assertions.assertEquals(voteCountResponseView.getVotesNo(), response.getVotesNo())
        );


    }

    private Vote createVote(Associate associate, VotingSession votingSession) {
        Vote vote = new Vote();
        vote.setId(10L);
        vote.setAssociate(associate);
        vote.setVotingSession(votingSession);
        return vote;
    }

    private Associate createAssociate(long l) {
        Associate associate = new Associate();
        associate.setId(l);
        associate.setAssociateName("Nathan");
        return associate;
    }

    private VotingSession createVoteSessionWithGivenTTL(Long ttlSession) {
        VotingSession votingSession = new VotingSession();
        votingSession.setId(10L);
        votingSession.setCreatedAt(new Date());
        votingSession.setVotingAgenda(new VotingAgenda());
        votingSession.setTtlVotingSession(ttlSession);
        return votingSession;
    }

    private VoteView createVoteView() {
        VoteView voteView = new VoteView();
        voteView.setVote(true);
        voteView.setAssociateId(10L);
        return voteView;
    }

}
