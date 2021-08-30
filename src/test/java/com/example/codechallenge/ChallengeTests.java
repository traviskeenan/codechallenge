package com.example.codechallenge;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.codechallenge.common.Account;
import com.example.codechallenge.common.Receiver;
import com.example.codechallenge.common.Sender;
import com.example.codechallenge.common.Transfer;
import com.example.codechallenge.exception.InsufficientFunds;
import com.example.codechallenge.exception.InvalidAccount;
import com.example.codechallenge.service.Challenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeTests {

    @Autowired
    private Challenge classUnderTest;

    private Utils utils;

    private Receiver receiver;
    private Sender sender;
    private Transfer transfer;
    private Account sendingAccount;
    private Account receivingAccount;

    @Before
    public void init() {
        sendingAccount = new Account();
        receivingAccount = new Account();

        List<Account> receivingAccounts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            receivingAccounts.add(utils.randomAccount());
        }

        receivingAccounts.add(receivingAccount);

        for (int i = 0; i < 5; i++) {
            receivingAccounts.add(utils.randomAccount());
        }

        List<Account> sendingAccounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sendingAccounts.add(utils.randomAccount());
        }
        sendingAccounts.add(sendingAccount);

        sender = new Sender();
        sender.setAccounts(sendingAccounts);
        sender.setId(UUID.randomUUID());

        receiver = new Receiver();
        receiver.setAccounts(receivingAccounts);
        receiver.setId(UUID.randomUUID());

    }

    @Test
    public void getAccount_findsAccountSender() {
        sendingAccount.setBalance(new BigDecimal(100));

        Account foundAccount = classUnderTest.getAccount(sender, sendingAccount.getId());

        assertEquals(sendingAccount.getId(), foundAccount.getId());
        assertEquals(sendingAccount.getBalance(), foundAccount.getBalance());
    }

    @Test
    public void getAccount_findsAccountReceiver() {
        receivingAccount.setBalance(new BigDecimal(100));

        Account foundAccount = classUnderTest.getAccount(receiver, receivingAccount.getId());

        assertEquals(receivingAccount.getId(), foundAccount.getId());
        assertEquals(receivingAccount.getBalance(), foundAccount.getBalance());
    }

    @Test(expected = InvalidAccount.class)
    public void transfer_InvalidSenderAccount() {
        transfer = new Transfer();
        transfer.setReceiver(receiver);
        transfer.setSender(sender);
        transfer.setSenderAccountId(UUID.randomUUID());
        transfer.setReceiverAccountId(receivingAccount.getId());

        classUnderTest.transfer(transfer);
    }

    @Test(expected = InvalidAccount.class)
    public void transfer_InvalidReceiverAccount() {
        transfer = new Transfer();
        transfer.setReceiver(receiver);
        transfer.setSender(sender);
        transfer.setSenderAccountId(sendingAccount.getId());
        transfer.setReceiverAccountId(UUID.randomUUID());

        classUnderTest.transfer(transfer);
    }

    @Test(expected = InsufficientFunds.class)
    public void transfer_InsufficientFunds() {
        transfer = new Transfer();
        sendingAccount.setBalance(new BigDecimal(11));
        transfer.setAmount(new BigDecimal(12));
        transfer.setReceiver(receiver);
        transfer.setSender(sender);
        transfer.setReceiverAccountId(receivingAccount.getId());
        transfer.setSenderAccountId(sendingAccount.getId());

        classUnderTest.transfer(transfer);
    }

    @Test
    public void transfer_successfulTransfer() {
        transfer = new Transfer();
        sendingAccount.setBalance(new BigDecimal(10));
        receivingAccount.setBalance(new BigDecimal(10));
        transfer.setAmount(new BigDecimal(10));
        transfer.setReceiver(receiver);
        transfer.setSender(sender);
        transfer.setReceiverAccountId(receivingAccount.getId());
        transfer.setSenderAccountId(sendingAccount.getId());

        classUnderTest.transfer(transfer);

        assertEquals(0, sendingAccount.getBalance().intValue());
        assertEquals(20, receivingAccount.getBalance().intValue());
    }
}