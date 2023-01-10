package com.voting.session.repository;


import com.voting.session.model.Associate;
import com.voting.session.model.Vote;
import com.voting.session.model.VotingAgenda;
import com.voting.session.model.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByAssociateAndVotingSessionVotingAgenda(Associate associate, VotingAgenda votingSession_votingAgenda);

    Long countVotesByVoteChoiceAndVotingSessionId(boolean voteChoice, Long votingSession_id);

}
