package com.voting.session.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Entity
@Table(name = "voting_agenda")
public class VotingAgenda extends AbstractPersistable<Long> {


    @Column
    private String votingTitle;

    @Column
    private Long votingTime;

    @Column
    private Date votingDate;

    @Column
    private Date createdAt;

    public String getVotingTitle() {
        return votingTitle;
    }

    public void setVotingTitle(String votingTitle) {
        this.votingTitle = votingTitle;
    }

    public Long getVotingTime() {
        return votingTime;
    }

    public void setVotingTime(Long votingTime) {
        this.votingTime = votingTime;
    }

    public Date getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(Date votingDate) {
        this.votingDate = votingDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
