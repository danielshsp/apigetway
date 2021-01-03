package com.vayusense.apigetway.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid parmams for user resources")
public class ResourceBadReqException extends RuntimeException {
    public ResourceBadReqException() {
        super();
    }

    public ResourceBadReqException(final String message) {
        super(message);
    }
}
