package com.memento.practice.api.exceptions;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(Long  id) {
        super("Application not found with id " + id);
    }
}
