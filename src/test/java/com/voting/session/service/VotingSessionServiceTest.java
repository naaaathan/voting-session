package com.voting.session.service;

import com.voting.session.exception.VotingAgendaDidNotStarted;
import com.voting.session.exception.VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime;
import com.voting.session.exception.VotingSessionNotFoundException;
import com.voting.session.model.VotingAgenda;
import com.voting.session.model.VotingSession;
import com.voting.session.repository.VotingSessionRepository;
import com.voting.session.service.impl.VotingSessionServiceImpl;
import com.voting.session.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VotingSessionServiceTest {

    private static VotingSessionService votingSessionService;

    private static VotingSessionRepository votingSessionRepository;

    private static VoteAgendaService voteAgendaService;

    @BeforeAll
    public static void setup() {
        votingSessionRepository = mock(VotingSessionRepository.class);
        voteAgendaService = mock(VoteAgendaService.class);
        votingSessionService = new VotingSessionServiceImpl(votingSessionRepository, voteAgendaService);

    }

    @Test
    public void findById_whenRegisterIsFoundByRepository_shouldReturnRegister() {

        Optional<VotingSession> votingSessionOptional = Optional.of(createNewVotingSession());

        when(votingSessionRepository.findById(anyLong())).thenReturn(votingSessionOptional);

        VotingSession votingSession = votingSessionService.findById(10L);

        Assertions.assertEquals(votingSession.getId(), 10L);

    }

    @Test
    public void findById_whenRegisterIsNotFoundByRepository_shouldThrowVotingSessionNotFoundException() {

        Optional<VotingSession> votingSessionOptional = Optional.empty();

        when(votingSessionRepository.findById(anyLong())).thenReturn(votingSessionOptional);

        Assertions.assertThrows(VotingSessionNotFoundException.class,
                () -> votingSessionService.findById(10L)
        );

    }

    @Test
    public void createNewVotingSession_whenEveryThingReturns_shouldReturnVotingSession() {

        VotingSession votingSession = createNewVotingSession();

        when(votingSessionRepository.save(any(VotingSession.class))).thenReturn(votingSession);
        when(voteAgendaService.findById(anyLong())).thenReturn(createNewVotingAgenda());

        VotingSession votingSessionResponse = votingSessionService.createNewVotingSession(10L);

        Assertions.assertAll("Assert voting session equals votingSessionResponse",
                () -> Assertions.assertEquals(votingSession.getId(), votingSessionResponse.getId()),
                () -> Assertions.assertEquals(votingSession.getTtlVotingSession(), votingSessionResponse.getTtlVotingSession()),
                () -> Assertions.assertEquals(votingSession.getCreatedAt(), votingSessionResponse.getCreatedAt())
        );
    }

    @Test
    public void createNewVotingSession_whenTheresAlreadyASessionRunningForTheVotingAgenda_shouldThrowVotingSessionAlreadyExistsForTheGivenAgendaAtThisTime() {

        VotingSession votingSession = createNewVotingSession();

        when(votingSessionRepository.save(any(VotingSession.class))).thenReturn(votingSession);
        when(voteAgendaService.findById(anyLong())).thenReturn(createNewVotingAgenda());
        when(votingSessionRepository.findVotingSessionByVotingAgendaIdAndVotingAgendaDate(any())).thenReturn(votingSession);

        Assertions.assertThrows(VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime.class,
                () -> votingSessionService.createNewVotingSession(10L)
        );
    }


    @Test
    public void createNewVotingSession_whenTheStartDateOfAgendaIsNotStarted_shouldThrowVotingAgendaDidNotStartedException() {

        VotingSession votingSession = createNewVotingSession();
        Date date = addTimeToCurrentDate();
        votingSession.getVotingAgenda().setBeginVotingDate(date);

        when(votingSessionRepository.save(any(VotingSession.class))).thenReturn(votingSession);
        when(voteAgendaService.findById(anyLong())).thenReturn(votingSession.getVotingAgenda());

        Assertions.assertThrows(VotingAgendaDidNotStarted.class,
                () -> votingSessionService.createNewVotingSession(10L)
        );
    }

    private static Date addTimeToCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 24);
        return calendar.getTime();
    }

    private VotingSession createNewVotingSession() {
        VotingSession votingSession = new VotingSession();
        votingSession.setCreatedAt(new Date());
        votingSession.setId(10L);
        votingSession.setTtlVotingSession(10L);
        votingSession.setVotingAgenda(createNewVotingAgenda());

        return votingSession;
    }

    private VotingAgenda createNewVotingAgenda() {
        VotingAgenda votingAgenda = new VotingAgenda();
        votingAgenda.setVotingTime(10L);
        votingAgenda.setCreatedAt(new Date());
        votingAgenda.setBeginVotingDate(new Date());
        votingAgenda.setVotingTitle("Votação assembleia cooperativa");
        return votingAgenda;
    }
}
