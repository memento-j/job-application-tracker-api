package com.memento.practice.api.exceptions;

import java.time.Instant;

//custom error response
public class ErrorResponse {
    private Integer status;
    private String code;
    private String message;
    private Instant timestamp = Instant.now();

    public ErrorResponse(Integer statusCode, String code, String message) {
        this.status = statusCode;
        this.code = code;
        this.message = message;
    }

    //getters to make the fields available for be serialized
    public Integer getStatusCode() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}
