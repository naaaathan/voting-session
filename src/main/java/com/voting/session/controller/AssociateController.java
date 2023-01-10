package com.voting.session.controller;

import com.voting.session.service.AssociateService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.AssociateView;
import com.voting.session.view.RestResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    private final AssociateService associateService;

    @Autowired
    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping("/createAssociate")
    public ResponseEntity<RestResponseView> createAssociate(@RequestBody AssociateView associateView) {

        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(associateService.saveAssociate(associateView), HttpStatus.OK));


    }


}
