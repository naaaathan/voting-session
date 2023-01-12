package com.voting.session.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.session.exception.VotingSessionNotFoundException;
import com.voting.session.service.VoteService;
import com.voting.session.view.VoteCountResponseView;
import com.voting.session.view.VoteResponseView;
import com.voting.session.view.VoteView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(value = VoteController.class)
public class VoteControllerTest {


    @MockBean
    private VoteService voteService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void voteInSessionOfAgenda_successScenario() throws Exception {
        VoteView voteView = createVoteView();
        VoteResponseView voteResponseView = createVoteResponseView();

        when(voteService.internilizeVote(anyLong(), any(VoteView.class))).thenReturn(voteResponseView);

        String jsonBody = createObjectAsJson(voteView);

        mockMvc.perform(MockMvcRequestBuilders.post("/vote/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.content().string(createVotePostRequestResponse_voteInSessionOfAgenda()))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void voteInSessionOfAgenda_whenNotBodyIsSent_shoudReturnBadRequest() throws Exception {
        VoteResponseView voteResponseView = createVoteResponseView();

        when(voteService.internilizeVote(anyLong(), any(VoteView.class))).thenReturn(voteResponseView);

        mockMvc.perform(MockMvcRequestBuilders.post("/vote/10"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void voteInSessionOfAgenda_whenVoteSessionAlreadyExpired_shoudReturnBadRequest() throws Exception {

        when(voteService.internilizeVote(anyLong(), any(VoteView.class))).thenThrow(VotingSessionNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/vote/10"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }


    @Test
    public void countVotesOfSession_successScenario() throws Exception {

        VoteCountResponseView voteCountResponseView = createVoteCountResponseView();

        when(voteService.countVotes(anyLong())).thenReturn(voteCountResponseView);

        String jsonBody = createObjectAsJson(voteCountResponseView);

        mockMvc.perform(MockMvcRequestBuilders.post("/vote/countVotes/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.content().string(createVotePostRequestResponse_countVotesOfSession()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    private String createVotePostRequestResponse_voteInSessionOfAgenda() {
        return "{\"response\":{\"id\":10,\"associateId\":10,\"vottingSessionId\":10},\"httpStatus\":\"OK\",\"error\":null}";
    }


    private String createVotePostRequestResponse_countVotesOfSession() {
        return "{\"response\":{\"votingAgendaId\":10,\"voteAgendaName\":\"Assembleia sobre aumento de tarifaÃ§Ã£o\",\"voteSessionId\":null,\"votesYes\":10,\"votesNo\":5},\"httpStatus\":\"OK\",\"error\":null}";
    }

    private VoteResponseView createVoteResponseView() {
        VoteResponseView voteResponseView = new VoteResponseView();
        voteResponseView.setId(10L);
        voteResponseView.setAssociateId(10L);
        voteResponseView.setVottingSessionId(10L);
        return voteResponseView;
    }


    private VoteCountResponseView createVoteCountResponseView() {
        VoteCountResponseView voteCountResponseView = new VoteCountResponseView();
        voteCountResponseView.setVotesYes(10L);
        voteCountResponseView.setVotesNo(5L);
        voteCountResponseView.setVoteAgendaName("Assembleia sobre aumento de tarifação");
        voteCountResponseView.setVotingAgendaId(10L);
        return voteCountResponseView;
    }


    private VoteView createVoteView() {
        VoteView voteView = new VoteView();
        voteView.setAssociateId(10L);
        voteView.setVote(true);

        return voteView;
    }

    private String createObjectAsJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);

    }


}
