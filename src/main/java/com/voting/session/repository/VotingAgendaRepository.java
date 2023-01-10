package com.voting.session.repository;

import com.voting.session.model.VotingAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VotingAgendaRepository extends JpaRepository<VotingAgenda, Long> {

    @Query("select va from VotingSession vs, VotingAgenda va where vs.votingAgenda = va and vs.id = :sessionId")
    VotingAgenda findVotingAgendaTitleByVotingSession(Long sessionId);

}
