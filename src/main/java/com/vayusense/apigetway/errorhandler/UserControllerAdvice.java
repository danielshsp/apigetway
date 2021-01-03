package com.vayusense.apigetway.errorhandler;

import com.vayusense.apigetway.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;

@ControllerAdvice(annotations = RestController.class)
public class UserControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handelNotFound(final ResourceNotFoundException exception, final  ServerHttpRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler({ResourceBadReqException.class, ValidationException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handelBadReq(final RuntimeException exception, final  ServerHttpRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handelBusiness(final BusinessException exception, final  ServerHttpRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setUri(request.getURI().toString());
        return ResponseEntity.status(error.getStatus()).body(error);
    }


}
