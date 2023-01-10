package com.voting.session.controller;

import com.voting.session.service.VoteAgendaService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.RestResponseView;
import com.voting.session.view.VoteAgendaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/agenda")
public class VoteAgendaController {

    private final VoteAgendaService voteAgendaService;

    @Autowired
    public VoteAgendaController(VoteAgendaService voteAgendaService) {
        this.voteAgendaService = voteAgendaService;
    }


    @PostMapping("/saveAgenda")
    public ResponseEntity<RestResponseView> newVotingAgenda(@Validated @RequestBody VoteAgendaView voteAgendaView) {

            return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(voteAgendaService.save(voteAgendaView), HttpStatus.OK));

    }


}
