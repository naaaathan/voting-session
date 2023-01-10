package com.voting.session.controller;

import com.voting.session.service.VoteService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.RestResponseView;
import com.voting.session.view.VoteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{voteSessionId}")
    public ResponseEntity<RestResponseView> voteInSessionOfAgenda(@PathVariable Long voteSessionId, @RequestBody VoteView voteView) {


        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteService.internilizeVote(voteSessionId, voteView), HttpStatus.OK));
    }

    @PostMapping("/countVotes/{voteSessionId}")
    public ResponseEntity<RestResponseView> countVotesOfSession(@PathVariable Long voteSessionId) {

        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteService.countVotes(voteSessionId), HttpStatus.OK));

    }
}
