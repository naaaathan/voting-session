package com.voting.session.controller;

import com.voting.session.service.VotingSessionService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.RestResponseView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voteSession")
public class VoteSessionController {

    private static final Logger LOGGER = LogManager.getLogger(VoteSessionController.class);

    private final VotingSessionService voteSessionService;

    @Autowired
    public VoteSessionController(VotingSessionService voteSessionService) {
        this.voteSessionService = voteSessionService;
    }

    @PostMapping("/startSession/{voteAgendaId}")
    public ResponseEntity<RestResponseView> initializeSessionVote(@PathVariable("voteAgendaId") int voteAgendaId) {

        LOGGER.info(String.format("Requested received for initializeSessionVote with voteAgendaId = %s", voteAgendaId));
        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteSessionService.createNewVotingSession((long) voteAgendaId), HttpStatus.OK));

    }


}
