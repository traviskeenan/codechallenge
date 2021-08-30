package com.example.codechallenge.exception;

public class InsufficientFunds extends TransferException {
    private static final long serialVersionUID = 1L;
 
    public InsufficientFunds() {
        super("Insufficient Funds");
    }
}