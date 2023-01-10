package com.voting.session.view;

public class VoteCountResponseView {

    private Long votingAgendaId;
    private String voteAgendaName;

    private Long voteSessionId;

    private Long votesYes;

    private Long votesNo;

    public String getVoteAgendaName() {
        return voteAgendaName;
    }

    public void setVoteAgendaName(String voteAgendaName) {
        this.voteAgendaName = voteAgendaName;
    }

    public Long getVoteSessionId() {
        return voteSessionId;
    }

    public void setVoteSessionId(Long voteSessionId) {
        this.voteSessionId = voteSessionId;
    }

    public Long getVotesYes() {
        return votesYes;
    }

    public void setVotesYes(Long votesYes) {
        this.votesYes = votesYes;
    }

    public Long getVotesNo() {
        return votesNo;
    }

    public void setVotesNo(Long votesNo) {
        this.votesNo = votesNo;
    }

    public Long getVotingAgendaId() {
        return votingAgendaId;
    }

    public void setVotingAgendaId(Long votingAgendaId) {
        this.votingAgendaId = votingAgendaId;
    }
}
