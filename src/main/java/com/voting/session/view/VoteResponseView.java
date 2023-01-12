package com.voting.session.view;

public class VoteResponseView {

    private Long id;

    private Long associateId;

    private Long vottingSessionId;


    public VoteResponseView() {
    }

    public VoteResponseView(Long id, Long associateId, Long vottingSessionId) {
        this.id = id;
        this.associateId = associateId;
        this.vottingSessionId = vottingSessionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAssociateId() {
        return associateId;
    }

    public void setAssociateId(long associateId) {
        this.associateId = associateId;
    }

    public long getVottingSessionId() {
        return vottingSessionId;
    }

    public void setVottingSessionId(long vottingSessionId) {
        this.vottingSessionId = vottingSessionId;
    }
}
