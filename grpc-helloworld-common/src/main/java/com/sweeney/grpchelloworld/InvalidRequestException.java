package com.sweeney.grpchelloworld;

public class InvalidRequestException extends Throwable {

    public InvalidRequestException(String message) {
        super(message);
    }
}
