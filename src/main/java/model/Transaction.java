package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    private int transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private double amount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "fromAccountFk")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "toAccountFk")
    private Account toAccount;

    public int getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return this.toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public String toString() {
        return "Transaction [transactionId=" + this.transactionId + ", transactionType=" + this.transactionType + ", transactionDate=" + this.transactionDate + ", amount=" + this.amount + ", description=" + this.description + ", fromAccount=" + this.fromAccount + ", toAccount=" + this.toAccount + "]";
    }

    public Transaction(int transactionId, TransactionType transactionType, LocalDate transactionDate, double amount, String description, Account fromAccount, Account toAccount) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public Transaction() {
    }
}


