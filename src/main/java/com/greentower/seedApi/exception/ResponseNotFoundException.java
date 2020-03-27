package com.greentower.seedApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResponseNotFoundException extends Exception {
    public ResponseNotFoundException(String message) {
        super(message);
    }
}
