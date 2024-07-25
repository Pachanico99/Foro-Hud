package com.challenge.api.infra.errors;

public class PageEmptyException extends Exception{
    public PageEmptyException(String message) {
        super(message);
    }
}
