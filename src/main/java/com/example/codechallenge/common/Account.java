package com.example.codechallenge.common;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID id;
    private BigDecimal balance;

    public Account() {
        this.id = UUID.randomUUID();
        this.balance = new BigDecimal(0);
    }

    public Account(UUID id) {
        this.id = id;
        this.balance = new BigDecimal(0);
    }

    public Account(UUID id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Account account = (Account) object;
            if (this.id.equals(account.getId())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}