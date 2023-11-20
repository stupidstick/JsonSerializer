package org.lab.json.serializer.exceptions;

public class NotJsonSerializableException extends RuntimeException{
    public NotJsonSerializableException(String message) {
        super(message);
    }
}
