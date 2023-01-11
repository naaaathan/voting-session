package com.voting.session.view;

import jakarta.validation.constraints.NotNull;

public class VoteView {

    @NotNull(message = "associateId field is mandatory")
    private Long associateId;

    @NotNull(message = "vote field is mandatory")
    private Boolean vote;

    public Long getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Long associateId) {
        this.associateId = associateId;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
