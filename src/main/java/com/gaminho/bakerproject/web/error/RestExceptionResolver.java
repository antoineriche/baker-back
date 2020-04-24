package com.gaminho.bakerproject.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@ControllerAdvice
public class RestExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionResolver.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleError(Exception exception) {
        log.info("{} caught", exception.getClass().getSimpleName());
        final Problem problem = Problem.builder().withTitle("Internal server error")
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .withDetail(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Problem> handleError(DataIntegrityViolationException exception) {
        log.error("DataIntegrityViolationException: {}", exception.getMessage(), exception.getMostSpecificCause());
        final Problem problem = Problem.builder().withTitle("Operation impossible")
                .withStatus(Status.BAD_REQUEST).withDetail(exception.getMessage()).build();
        return ResponseEntity.status(Status.BAD_REQUEST.getStatusCode()).body(problem);
    }
}
