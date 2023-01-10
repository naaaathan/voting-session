package com.voting.session.model;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity
@Table(name = "voting_session")
public class VotingSession {


    @Id
    @GeneratedValue
    @Nullable
    private Long id;

    @ManyToOne
    @JoinColumn
    private VotingAgenda votingAgenda;

    @Column
    private Long ttlVotingSession;

    @Column
    private Date createdAt;


    public VotingAgenda getVotingAgenda() {
        return votingAgenda;
    }

    public void setVotingAgenda(VotingAgenda votingAgenda) {
        this.votingAgenda = votingAgenda;
    }

    public Long getTtlVotingSession() {
        return ttlVotingSession;
    }

    public void setTtlVotingSession(Long ttlVotingSession) {
        this.ttlVotingSession = ttlVotingSession;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }
}
