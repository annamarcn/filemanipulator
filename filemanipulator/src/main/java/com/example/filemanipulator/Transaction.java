package com.example.filemanipulator;

import java.util.Date;

public class Transaction{
    Date accountingDate;
    Date transactionDate;
    String description;
    float amount;
    float balance;

    public Transaction(Date accountingDate, Date transactionDate, String description, float amount, float balance) {
        this.accountingDate = accountingDate;
        this.transactionDate = transactionDate;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}

