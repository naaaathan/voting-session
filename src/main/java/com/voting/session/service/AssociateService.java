package com.voting.session.service;

import com.voting.session.exception.AssociateNotFoundException;
import com.voting.session.exception.DateFormatException;
import com.voting.session.model.Associate;
import com.voting.session.view.AssociateView;

public interface AssociateService {

    public Associate saveAssociate(AssociateView associateView) throws DateFormatException;


    public Associate findById(Long id) throws AssociateNotFoundException;

}
