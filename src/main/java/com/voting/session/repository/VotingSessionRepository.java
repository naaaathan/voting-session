package com.voting.session.repository;

import com.voting.session.model.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

    @Query("select vs from VotingSession vs where vs.createdAt + vs.ttlVotingSession > :actualDate ")
    VotingSession findVotingSessionByVotingAgendaIdAndVotingAgendaDate(Date actualDate);


}
