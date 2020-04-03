package com.greentower.seedApi.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResponseNotFoundException extends RuntimeException {
    public ResponseNotFoundException(String message) { super(message); }
}
