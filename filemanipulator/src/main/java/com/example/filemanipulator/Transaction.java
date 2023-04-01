package com.example.filemanipulator;

import java.time.LocalDate;
import java.util.Date;

public class Transaction{
    LocalDate accountingDate;
    LocalDate transactionDate;
    String description;
    float amount;
    float balance;

    public Transaction(LocalDate accountingDate, LocalDate transactionDate, String description, float amount, float balance) {
        this.accountingDate = accountingDate;
        this.transactionDate = transactionDate;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
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

