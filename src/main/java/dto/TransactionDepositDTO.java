package dto;

public class TransactionDepositDTO {
    int customerId;
    int accountNumber;
    double amount;
    String description;
    int fromAccountNumber;

    public TransactionDepositDTO() {
    }

    public TransactionDepositDTO(int customerId, int accountNumber, double amount, String description, int fromAccountNumber) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
        this.fromAccountNumber = fromAccountNumber;
    }

    public String toString() {
        return "TransactionDepositDTO [customerId=" + this.customerId + ", accountNumber=" + this.accountNumber + ", amount=" + this.amount + ", description=" + this.description + ", fromAccountNumber=" + this.fromAccountNumber + "]";
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
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

    public int getFromAccountNumber() {
        return this.fromAccountNumber;
    }

    public void setFromAccountNumber(int fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }
}
