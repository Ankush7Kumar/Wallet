package model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Account {

    @Id
    private int accountNumber;

    @ManyToOne
    @JoinColumn(name="customerFk")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private double openingBalance;
    private LocalDate openingDate;
    private String description;

    public String toString() {
        return "Account [accountNumber=" + this.accountNumber + ", customer=" + this.customer + ", accountType=" + this.accountType + ", openingBalance=" + this.openingBalance + ", openingDate=" + this.openingDate + ", description=" + this.description + "]";
    }

    public Account() {
    }

    public Account(int accountNumber, Customer customer, AccountType accountType, double openingBalance, LocalDate openingDate, String description) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
        this.openingDate = openingDate;
        this.description = description;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getOpeningBalance() {
        return this.openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public LocalDate getOpeningDate() {
        return this.openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
