package com.voting.session.handler;

import com.voting.session.exception.*;
import com.voting.session.utils.RestApiErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Component
public class RestExceptionHandler extends ExceptionsHandler {

    @ExceptionHandler(AssociateNotFoundException.class)
    public ResponseEntity<RestApiErrorMessage> handleAssociateNotFoundException(AssociateNotFoundException exception, WebRequest webRequest) {
        return super.handleExceptionNotFound(exception, webRequest);
    }

    @ExceptionHandler(AssociateAlreadyVotedInThisAgenda.class)
    public ResponseEntity<RestApiErrorMessage> handleAssociateAlreadyVotedInThisAgendaException(AssociateAlreadyVotedInThisAgenda exception, WebRequest webRequest) {
        return super.handleBadRequestExceptions(exception, webRequest);
    }

    @ExceptionHandler(DateFormatException.class)
    public ResponseEntity<RestApiErrorMessage> handleDateFormatExceptionException(DateFormatException exception, WebRequest webRequest) {
        return super.handleBadRequestExceptions(exception, webRequest);
    }

    @ExceptionHandler(VoteSessionAlreadyExpired.class)
    public ResponseEntity<RestApiErrorMessage> handleVoteSessionAlreadyExpiredException(VoteSessionAlreadyExpired exception, WebRequest webRequest) {
        return super.handleBadRequestExceptions(exception, webRequest);
    }

    @ExceptionHandler(VotingAgendaNotFoundException.class)
    public ResponseEntity<RestApiErrorMessage> handleVotingAgendaNotFoundException(VotingAgendaNotFoundException exception, WebRequest webRequest) {
        return super.handleExceptionNotFound(exception, webRequest);
    }

    @ExceptionHandler(VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime.class)
    public ResponseEntity<RestApiErrorMessage> handleVotingSessionAlreadyExistsForTheGivenAgendaAtThisTimeException(VotingSessionAlreadyExistsForTheGivenAgendaAtThisTime exception, WebRequest webRequest) {
        return super.handleBadRequestExceptions(exception, webRequest);
    }

    @ExceptionHandler(VotingSessionNotEndedException.class)
    public ResponseEntity<RestApiErrorMessage> handleVotingSessionNotEndedExceptionException(VotingSessionNotEndedException exception, WebRequest webRequest) {
        return super.handleBadRequestExceptions(exception, webRequest);
    }

    @ExceptionHandler(VotingSessionNotFoundException.class)
    public ResponseEntity<RestApiErrorMessage> handleVotingSessionNotFoundExceptionException(VotingSessionNotFoundException exception, WebRequest request) {
        return super.handleExceptionNotFound(exception, request);
    }

    @ExceptionHandler(VotingAgendaDidNotStarted.class)
    public ResponseEntity<RestApiErrorMessage> handleVotingAgendaDidNotStartedException(VotingAgendaDidNotStarted exception, WebRequest request) {
        return super.handleBadRequestExceptions(exception, request);
    }

}
