package org.lab.serializer.exceptions;

public class NotJsonSerializableException extends RuntimeException{
    public NotJsonSerializableException(String message) {
        super(message);
    }
}
