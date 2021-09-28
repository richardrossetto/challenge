package com.challenge.exceptionhandler;

import com.challenge.domain.exception.PersonException;
import com.challenge.domain.exception.VaccinationCenterException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not Found.", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(VaccinationCenterException.class)
    public ResponseEntity<StandardError> vaccinationCenterException(VaccinationCenterException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Check Vaccination Center fields.", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(PersonException.class)
    public ResponseEntity<StandardError> personException(PersonException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Person error.", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String msg = "";
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            msg += violation.getMessage() + ", ";
        }
        StandardError err = new StandardError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Validation field failed,", msg.substring(0, msg.length() - 2), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

}
