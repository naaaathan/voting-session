package com.voting.session.view;

import com.voting.session.model.VotingAgenda;

public class VotingSessionView {

    private VotingAgenda votingAgenda;
    private Long ttlVotingSession;

    public Long getTtlVotingSession() {
        return ttlVotingSession;
    }

    public void setTtlVotingSession(long ttlVotingSession) {
        this.ttlVotingSession = ttlVotingSession;
    }

    public VotingAgenda getVotingAgenda() {
        return votingAgenda;
    }

    public void setVotingAgenda(VotingAgenda votingAgenda) {
        this.votingAgenda = votingAgenda;
    }
}
