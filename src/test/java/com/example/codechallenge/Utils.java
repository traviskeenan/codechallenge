package com.example.codechallenge;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.codechallenge.common.Account;

public class Utils {
    public static Account randomAccount() {
        return new Account(UUID.randomUUID(), new BigDecimal(Math.random()));
    }

    public static Account randomAccount(BigDecimal amount) {
        return new Account(UUID.randomUUID(), amount);
    }
}