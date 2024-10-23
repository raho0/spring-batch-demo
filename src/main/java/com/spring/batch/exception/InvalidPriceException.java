package com.spring.batch.exception;

public class InvalidPriceException extends RuntimeException {

    public InvalidPriceException(String string) {
        super(string);
    }

}
