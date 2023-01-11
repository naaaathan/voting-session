package com.voting.session.controller;

import com.voting.session.service.VoteService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.RestResponseView;
import com.voting.session.view.VoteView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private static final Logger LOGGER = LogManager.getLogger(VoteController.class);

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{voteSessionId}")
    public ResponseEntity<RestResponseView> voteInSessionOfAgenda(@PathVariable Long voteSessionId, @RequestBody VoteView voteView) {

        LOGGER.info(String.format("Received a request for vote in voteSession with id %s and body %s", voteSessionId, voteView));
        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteService.internilizeVote(voteSessionId, voteView), HttpStatus.OK));
    }

    @PostMapping("/countVotes/{voteSessionId}")
    public ResponseEntity<RestResponseView> countVotesOfSession(@PathVariable Long voteSessionId) {

        LOGGER.info(String.format("Received a request for count votes of session with id %s", voteSessionId));
        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteService.countVotes(voteSessionId), HttpStatus.OK));

    }
}
