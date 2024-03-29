package com.voting.session.controller;

import com.voting.session.service.VoteAgendaService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.RestResponseView;
import com.voting.session.view.VoteAgendaView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
public class VoteAgendaController {

    private static final Logger LOGGER = LogManager.getLogger(VoteAgendaController.class);

    private final VoteAgendaService voteAgendaService;

    @Autowired
    public VoteAgendaController(VoteAgendaService voteAgendaService) {
        this.voteAgendaService = voteAgendaService;
    }


    @PostMapping("/saveAgenda")
    public ResponseEntity<RestResponseView> newVotingAgenda(@Validated @RequestBody VoteAgendaView voteAgendaView) {

        LOGGER.info(String.format("Received a request to create a new voting agenda with body %s", voteAgendaView));
        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteAgendaService.save(voteAgendaView), HttpStatus.OK));

    }


}
