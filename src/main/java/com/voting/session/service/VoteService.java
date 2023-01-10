package com.voting.session.service;

import com.voting.session.model.Vote;
import com.voting.session.view.VoteCountResponseView;
import com.voting.session.view.VoteResponseView;
import com.voting.session.view.VoteView;

public interface VoteService {

    VoteResponseView internilizeVote(Long sessionId, VoteView voteView);

    Vote save(Vote vote);

    VoteCountResponseView countVotes(Long sessionId);

}
