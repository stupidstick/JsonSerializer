package org.lab.serializer.exceptions;

public class NotJsonFormatException extends RuntimeException {
    public NotJsonFormatException(String message) {
        super(message);
    }
}
