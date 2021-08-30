package com.example.codechallenge.service;

import java.util.List;
import java.util.UUID;

import com.example.codechallenge.common.Account;
import com.example.codechallenge.common.Receiver;
import com.example.codechallenge.common.Sender;
import com.example.codechallenge.common.Transfer;
import com.example.codechallenge.exception.InvalidAccount;
import com.example.codechallenge.exception.TransferException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Challenge {
    @Autowired
    private TransferUtils transferUtils;

    public Account getAccount(Receiver receiver, UUID accountId) {
        List<Account> accounts = receiver.getAccounts();

        if (accounts.contains(new Account(accountId))) {
            return findAccount(accounts, accountId);
        } else {
            throw new InvalidAccount();
        }
    }

    public Account getAccount(Sender sender, UUID accountId) {
        List<Account> accounts = sender.getAccounts();

        if (accounts.contains(new Account(accountId))) {
            return findAccount(accounts, accountId);
        } else {
            throw new InvalidAccount();
        }
    }

    // Challenge #1
    // TODO: find correct account
    private Account findAccount(List<Account> accounts, UUID accountId) {
        Account foundAccount = null;

        for (int i = 0; i < accounts.size(); i++) {
            Account accountToCheck = accounts.get(i);
            if (accountToCheck.getId() == accountId) {
                foundAccount = accountToCheck;
            }
            i++;
        }

        return foundAccount;
    }

    // Challenge #2
    // TODO: check funds for sending account
    // Is there a function in TransferUtils that would work for this?
    public void transfer(Transfer transfer) throws TransferException {
        Account sendingAccount = getAccount(transfer.getSender(), transfer.getSenderAccountId());
        Account receivingAccount = getAccount(transfer.getReceiver(), transfer.getReceiverAccountId());

        transferUtils.transfer(sendingAccount, receivingAccount, transfer.getAmount());
    }

}