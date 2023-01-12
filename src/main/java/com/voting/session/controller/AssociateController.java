package com.voting.session.controller;

import com.voting.session.service.AssociateService;
import com.voting.session.utils.ResponseUtils;
import com.voting.session.view.AssociateView;
import com.voting.session.view.RestResponseView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    private static final Logger LOGGER = LogManager.getLogger(AssociateController.class);

    private final AssociateService associateService;

    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping("/createAssociate")
    public ResponseEntity<RestResponseView> createAssociate(@RequestBody AssociateView associateView) {

        LOGGER.info(String.format("Begin create associate request received with body %s", associateView));
        return ResponseEntity.ok().body(ResponseUtils.createRestResponseView(associateService.saveAssociate(associateView), HttpStatus.OK));

    }


}
