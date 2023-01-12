package com.voting.session.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.session.model.Associate;
import com.voting.session.service.AssociateService;
import com.voting.session.view.AssociateView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(value = AssociateController.class)
public class AssociateControllerTest {

    @MockBean
    private AssociateService associateService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void createAssociate_whenSaveOperationSucceded_shouldAssertContent() throws Exception {

        AssociateView associateView = createAssociateView();
        Associate associate = createAssociate();

        when(associateService.saveAssociate(any(AssociateView.class))).thenReturn(associate);

        String jsonBody = createObjectAsJson(associateView);

        mockMvc.perform(MockMvcRequestBuilders.post("/associate/createAssociate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.content().string(createAssociatePostRequestResponse()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    private String createObjectAsJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);

    }

    private String createAssociatePostRequestResponse() {

        return  "{\"response\":{\"id\":10,\"new\":false},\"httpStatus\":\"OK\",\"error\":null}";
    }


    public AssociateView createAssociateView() {
        AssociateView associateView = new AssociateView();
        associateView.setName("Nathan");
        associateView.setPrimaryDocumentNumber("3092183920138");
        associateView.setBirthDate("26/04/2000");

        return associateView;

    }

    public Associate createAssociate() {

        Associate associate = new Associate();
        associate.setAssociateName("Nathan");
        associate.setId(10L);

        return associate;
    }

}
