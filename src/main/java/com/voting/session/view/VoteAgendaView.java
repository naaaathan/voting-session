package com.voting.session.view;


import jakarta.validation.constraints.NotNull;

public class VoteAgendaView {

    @NotNull(message = "votingTitle field is mandatory")
    private String votingTitle;

    @NotNull(message = "votingTime field is mandatory")
    private Long votingTime;

    @NotNull(message = "beginVotingDate field is mandatory")
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

    @Override
    public String toString() {
        return "VoteAgendaView{" +
                "votingTitle='" + votingTitle + '\'' +
                ", votingTime=" + votingTime +
                ", beginVotingDate='" + beginVotingDate + '\'' +
                '}';
    }
}
