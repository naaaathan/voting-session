package com.voting.session.service;


import com.voting.session.exception.AssociateNotFoundException;
import com.voting.session.repository.AssociateRepository;
import com.voting.session.service.impl.AssociateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AssociateServiceTest {

    private static AssociateServiceImpl associateService;

    private static AssociateRepository associateRepository;

    @BeforeAll
    public static void setup() {
        associateRepository = mock(AssociateRepository.class);
        associateService = new AssociateServiceImpl(associateRepository);
    }


    @Test
    public void findById_shouldThrowAssociateNotFoundException_whenRepositoryReturnsEmpty() {

        when(associateRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(AssociateNotFoundException.class, () -> {
            associateService.findById(10L);
        },"AssociateNotFoundException expected");

    }

}
