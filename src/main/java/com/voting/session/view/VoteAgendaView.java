package com.voting.session.view;


public class VoteAgendaView {


    private String votingTitle;


    private Long votingTime;


    private String beginVotingDate;

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

    public String getBeginVotingDate() {
        return beginVotingDate;
    }

    public void setBeginVotingDate(String beginVotingDate) {
        this.beginVotingDate = beginVotingDate;
    }
}
