package com.example.codechallenge.service;

import java.math.BigDecimal;
import com.example.codechallenge.common.Account;
import org.springframework.stereotype.Component;
 
@Component
public class TransferUtils {
    public Boolean hasSufficientFunds(Account account, BigDecimal amount){
        if (account.getBalance().compareTo(amount) >= 0 ) {
            return true;
        } else {
            return false;
        }
    }
 
    public void transfer(Account sendingAccount, Account receivingAccount, BigDecimal amount){
        BigDecimal sendingBalance = sendingAccount.getBalance().subtract(amount);
        sendingAccount.setBalance(sendingBalance);
 
        BigDecimal receivingBalance = receivingAccount.getBalance().add(amount);
        receivingAccount.setBalance(receivingBalance);
    }
 
}