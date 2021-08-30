package com.example.codechallenge.common;

import java.math.BigDecimal;
import java.util.UUID;

public class Transfer {
    private Sender sender;
    private UUID senderAccountId;
    private Receiver receiver;
    private UUID receiverAccountId;
    private BigDecimal amount;

    /**
     * @return the receiver
     */
    public Receiver getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the sender
     */
    public Sender getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    /**
     * @return the receiverAccountId
     */
    public UUID getReceiverAccountId() {
        return receiverAccountId;
    }

    /**
     * @param receiverAccountId the receiverAccountId to set
     */
    public void setReceiverAccountId(UUID receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    /**
     * @return the senderAccountId
     */
    public UUID getSenderAccountId() {
        return senderAccountId;
    }

    /**
     * @param senderAccountId the senderAccountId to set
     */
    public void setSenderAccountId(UUID senderAccountId) {
        this.senderAccountId = senderAccountId;
    }
}