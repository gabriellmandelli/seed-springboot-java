package com.greentower.seedApi.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResponseBadRequestException extends RuntimeException {
    public ResponseBadRequestException(String message){
        super(message);
    }
}
