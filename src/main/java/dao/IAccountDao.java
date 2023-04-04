package dao;

import model.Account;
import model.Customer;

import java.util.List;

public interface IAccountDao {

    Account addAccount(Customer var1, Account var2);
    Account findAccount(int var1, Customer var2);

    List<Account> getAllAccounts();

    List<Account> getAllAccountsByCustomer(Customer var1);

    void deleteAccount(int var1);
}
