package com.vayusense.apigetway.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server error for user resources")
public class BusinessException  extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
    }
}
