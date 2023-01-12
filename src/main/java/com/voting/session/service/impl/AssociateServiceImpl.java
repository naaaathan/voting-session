package com.voting.session.service.impl;

import com.voting.session.exception.AssociateNotFoundException;
import com.voting.session.model.Associate;
import com.voting.session.repository.AssociateRepository;
import com.voting.session.service.AssociateService;
import com.voting.session.utils.DateUtils;
import com.voting.session.view.AssociateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;

    @Autowired
    public AssociateServiceImpl(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Override
    public Associate saveAssociate(AssociateView associateView) {

        return associateRepository.save(createAssociteModel(associateView));
    }

    @Override
    public Associate findById(Long id) throws AssociateNotFoundException {
        Optional<Associate> associate = associateRepository.findById(id);

        if (associate.isPresent()) {
            return associate.get();
        }

        throw new AssociateNotFoundException("Associate not found for the given id.");
    }

    private Associate createAssociteModel(AssociateView associateView) {

        Associate associate = new Associate();

        associate.setAssociateBirth(DateUtils.formatDate(associateView.getBirthDate()));
        associate.setAssociateName(associateView.getName());
        associate.setAssociatePrimaryDocument(associateView.getPrimaryDocumentNumber());

        return associate;
    }
}
