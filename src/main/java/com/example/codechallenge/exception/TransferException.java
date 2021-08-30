package com.example.codechallenge.exception;

public abstract class TransferException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransferException(String message) {
        super(message);
    }
}
