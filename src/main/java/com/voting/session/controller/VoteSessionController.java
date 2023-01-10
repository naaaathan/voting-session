package com.voting.session.controller;

import com.voting.session.service.VotingSessionService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.RestResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voteSession")
public class VoteSessionController {

    private final VotingSessionService voteSessionService;

    @Autowired
    public VoteSessionController(VotingSessionService voteSessionService) {
        this.voteSessionService = voteSessionService;
    }

    @PostMapping("/startSession/{voteAgendaId}")
    public ResponseEntity<RestResponseView> initializeSessionVote(@RequestHeader Integer ttlVoteSession, @PathVariable("voteAgendaId") int voteAgendaId) {

        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteSessionService.createNewVotingSession(ttlVoteSession, (long) voteAgendaId), HttpStatus.OK));

    }


}
