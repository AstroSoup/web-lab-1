package ru.astrosoup.exceptions;

public class UnsupportedEndpointException extends Exception {
    public UnsupportedEndpointException() {
    }
    public UnsupportedEndpointException(String message) {
        super(message);
    }
}
