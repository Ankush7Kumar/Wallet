package dao;

import model.Customer;

public interface ICustomerDao {
    Customer registerCustomer(Customer var1);

    Customer validateLogin(String var1, String var2);

    Customer findCustomer(int var1);
}
