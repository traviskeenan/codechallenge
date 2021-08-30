package com.example.codechallenge.exception;

public class InvalidAccount extends TransferException {
    private static final long serialVersionUID = 1L;
 
    public InvalidAccount() {
        super("Invalid Account");
    }
}