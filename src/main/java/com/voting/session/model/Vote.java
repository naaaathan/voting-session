package com.voting.session.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue
    @Nullable
    private Long id;
    @OneToOne
    @JoinColumn
    private Associate associate;

    @OneToOne
    @JoinColumn
    private VotingSession votingSession;

    @Column
    private boolean voteChoice;

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public VotingSession getVotingSession() {
        return votingSession;
    }

    public void setVotingSession(VotingSession votingSession) {
        this.votingSession = votingSession;
    }

    public boolean isVoteChoice() {
        return voteChoice;
    }

    public void setVoteChoice(boolean voteChoice) {
        this.voteChoice = voteChoice;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }
}
