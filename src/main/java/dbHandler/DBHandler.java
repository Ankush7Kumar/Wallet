package dbHandler;


import dto.TransactionDepositDTO;
import jakarta.persistence.*;
import model.Account;
import model.AccountType;
import model.Address;
import model.Customer;
import model.Gender;
import model.Transaction;
import model.TransactionType;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class DBHandler {
    public DBHandler() {
    }


    public Connection getDBCon() {

        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wallets?characterEncoding=utf8", "root", "ucantcme");

            System.out.println("connection established successfully");

        } catch (Exception e) {
            System.out.println("jdbc connection ke exception ke bhitar");
            System.out.println(e);
            e.printStackTrace();
        }

        return con;
    }

    public int getId(String tblName, String colName) {

        int res = 1;
        Connection con = this.getDBCon();
        try {
            PreparedStatement stmt = con.prepareStatement("select max(" + colName + ") as res from " + tblName);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                res = rset.getInt("res");
                res = res + 1;
            } else {
                res = 1;
            }
            con.close();
        } catch (SQLException var7) {
            var7.printStackTrace();
            return res;
        }
        return res;
    }


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    public Address saveAddress(Address add) {
        System.out.println("SA1");
        try {
            System.out.println("SA2");
            transaction.begin();
            System.out.println("SA3");
            entityManager.persist(add);
            System.out.println("SA4");
            //transaction.commit();
            System.out.println("SA5");
        } catch (Exception e) {
            System.out.println("yeh sysytem dikkat hai");
            e.printStackTrace();
        }finally {
            System.out.println("SA6");
            if (transaction.isActive()) {
                System.out.println("SA7");
                //transaction.rollback();
            }
            System.out.println("SA8");

            //entityManager.close();
            //entityManagerFactory.close();
            System.out.println("SA9");
        }
        return add;


        /*
        //------------
        Address a = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("insert into address values (?,?,?,?,?,?)");
            stmt.setInt(1, add.getAddressId());
            stmt.setString(2, add.getAddressLine1());
            stmt.setString(3, add.getAddressLine2());
            stmt.setString(4, add.getCity());
            stmt.setString(5, add.getState());
            stmt.setString(6, add.getPincode());
            int res = stmt.executeUpdate();
            if (res == 0) {
                a = null;
            } else {
                a = add;
                System.out.println("address saved successfully");
            }

            con.close();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return a;

         */
    }

    public Transaction saveTransaction(Transaction trans) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(trans);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        return trans;
        /*
        Transaction t = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("insert into transaction values (?,?,?,?,?,?,?)");
            stmt.setInt(1, trans.getTransactionId());
            String transactionType = "";
            if (trans.getTransactionType() == TransactionType.CREDIT) {
                transactionType = "CREDIT";
            } else if (trans.getTransactionType() == TransactionType.DEBIT) {
                transactionType = "DEBIT";
            }

            stmt.setString(2, transactionType);
            LocalDate ldtTransactionDate = trans.getTransactionDate();
            String strTransDate = ldtTransactionDate.getDayOfMonth() + "-" + ldtTransactionDate.getMonthValue() + "-" + ldtTransactionDate.getYear();
            stmt.setString(3, strTransDate);
            stmt.setDouble(4, trans.getAmount());
            stmt.setString(5, trans.getDescription());
            stmt.setInt(6, trans.getFromAccount().getAccountNumber());
            stmt.setInt(7, trans.getToAccount().getAccountNumber());
            int res = stmt.executeUpdate();
            if (res == 0) {
                t = null;
            } else {
                t = trans;
                System.out.println("trans saved successfully");
            }

            con.close();
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        return t;

         */
    }

    public Account saveAccount(Account acc) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(acc);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        return acc;

        /*
        Account a = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("insert into account values (?,?,?,?,?,?)");
            stmt.setInt(1, acc.getAccountNumber());
            stmt.setInt(2, acc.getCustomer().getCustomerId());
            String accType = "";
            if (acc.getAccountType() == AccountType.CURRENT) {
                accType = "CURRENT";
            } else if (acc.getAccountType() == AccountType.FD) {
                accType = "FD";
            } else if (acc.getAccountType() == AccountType.LOAN) {
                accType = "LOAN";
            } else if (acc.getAccountType() == AccountType.RD) {
                accType = "RD";
            } else if (acc.getAccountType() == AccountType.SALARY) {
                accType = "SALARY";
            } else if (acc.getAccountType() == AccountType.SAVINGS) {
                accType = "SAVINGS";
            }

            stmt.setString(3, accType);
            stmt.setDouble(4, acc.getOpeningBalance());
            stmt.setString(5, acc.getDescription());
            LocalDate ldtOpeningDate = acc.getOpeningDate();
            String strOpeningDate = ldtOpeningDate.getDayOfMonth() + "-" + ldtOpeningDate.getMonthValue() + "-" + ldtOpeningDate.getYear();
            stmt.setString(6, strOpeningDate);
            int res = stmt.executeUpdate();
            if (res == 0) {
                a = null;
            } else {
                a = acc;
                System.out.println("Account saved successfully");
            }

            con.close();
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        return a;

         */
    }

    public Customer getCustomerByEmailidAndPwd(String strEmailId) {
        Customer cust = null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        try {
            transaction.begin();
            String query2="from Customer c where c.emailId=?1";
            Query q2=entityManager.createQuery(query2);
            q2.setParameter(1, strEmailId);


            List<Customer> lst=q2.getResultList();
            if (!(lst.isEmpty())){
                cust = lst.get(0);
            }
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        return cust;
        /*
        Customer cust = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from customer where emailid=?");
            stmt.setString(1, strEmailId);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                int customerId = rset.getInt("customerId");
                String firstName = rset.getString("firstName");
                String lastName = rset.getString("lastName");
                String emailId = rset.getString("emailid");
                String contactNo = rset.getString("contactNo");
                int addressId = rset.getInt("addressId");
                Address address = this.getAddressByAddressId(addressId);
                String strGender = rset.getString("gender");
                Gender gender;
                if (strGender.equals(Gender.MALE)) {
                    gender = Gender.MALE;
                } else {
                    gender = Gender.FEMALE;
                }

                String password = rset.getString("pwd");
                String confirmPassword = "";
                String strRegDate = rset.getString("registerationDate");
                System.out.println(strRegDate);
                LocalDate registerationDate = LocalDate.parse(strRegDate, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"));
                cust = new Customer(customerId, firstName, lastName, emailId, contactNo, address, gender, password, confirmPassword, registerationDate);
                System.out.println(cust);
            }

            con.close();
        } catch (SQLException var19) {
            var19.printStackTrace();
        }

        return cust;

         */
    }

    public Address getAddressByAddressId(int addressId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Address ad1 = null;
        try {
            transaction.begin();
            ad1 = entityManager.find(Address.class, addressId);

            if (ad1 == null) {
                throw new EntityNotFoundException("Can't find account for ID "
                        + addressId);
            }
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return ad1;
        /*
        Address add = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from Address where addressId=?");
            stmt.setInt(1, addressId);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                String addressLine1 = rset.getString("addressLine1");
                String addressLine2 = rset.getString("addressLine2");
                String city = rset.getString("city");
                String state = rset.getString("state");
                String pincode = rset.getString("pincode");
                add = new Address(addressId, addressLine1, addressLine2, city, state, pincode);
            }

            con.close();
        } catch (SQLException var11) {
            var11.printStackTrace();
        }

        return add;

         */
    }

    public boolean isValidCustByEmailidAndPwd(String strEmailId, String strPwd) {
        boolean retVal = false;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        try {
            transaction.begin();
            String query2="from Customer c where c.emailId=?1 or c.password=?2";
            Query q2=entityManager.createQuery(query2);
            q2.setParameter(1, strEmailId);
            q2.setParameter(2, strPwd);

            List<Customer> lst2=q2.getResultList();
            if (!(lst2.isEmpty())){
                retVal = true;
            }


            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        return retVal;

        /*
        boolean res = false;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from customer where emailid=? and pwd=?");
            stmt.setString(1, strEmailId);
            stmt.setString(2, strPwd);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                res = true;
            } else {
                res = false;
            }

            con.close();
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        return res;

         */
    }

    public Customer saveCustomer(Customer cust) {
        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        //EntityTransaction transaction = entityManager.getTransaction();
        System.out.println("SC1");
        try {
            System.out.println("SC2");
            //transaction.begin();
            System.out.println("SC3");
            entityManager.persist(cust);
            System.out.println("SC4");
            transaction.commit();
            System.out.println("SC5");
        } finally {
            if (transaction.isActive()) {
                System.out.println("SC6");
                //transaction.rollback();
            }
            System.out.println("SC7");
            entityManager.close();
            System.out.println("SC8");
            entityManagerFactory.close();
        }

        return cust;

        /*
        Customer c = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("insert into customer values (?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, cust.getCustomerId());
            stmt.setString(2, cust.getFirstName());
            stmt.setString(3, cust.getLastName());
            stmt.setString(4, cust.getEmailId());
            stmt.setString(5, cust.getContactNo());
            stmt.setInt(6, cust.getAddress().getAddressId());
            String strGender = "";
            if (cust.getGender() == Gender.MALE) {
                strGender = "Male";
            } else if (cust.getGender() == Gender.FEMALE) {
                strGender = "Female";
            }

            stmt.setString(7, strGender);
            stmt.setString(8, cust.getPassword());
            LocalDate ldtRegDate = cust.getRegisterationDate();
            String strRegDate = ldtRegDate.getDayOfMonth() + "-" + ldtRegDate.getMonthValue() + "-" + ldtRegDate.getYear();
            stmt.setString(9, strRegDate);
            int res = stmt.executeUpdate();
            con.close();
            if (res == 0) {
                c = null;
            } else {
                System.out.println("Customer registerd successfully");
                c = cust;
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        return c;

         */
    }

    public Transaction WithDrawAmountfromAccount(TransactionDepositDTO tdd) {
        int accountNumber = tdd.getAccountNumber();
        Account acc = this.getAccountByAccNumber(accountNumber);
        double newOpeningBalance = acc.getOpeningBalance() - tdd.getAmount();
        this.updateOpeningBalanceByAccountNumber(tdd.getAccountNumber(), newOpeningBalance);
        int transactionId = this.getId("transaction", "transactionId");
        TransactionType transactionType = TransactionType.DEBIT;
        LocalDate transactionDate = LocalDate.now();
        System.out.println("todays date is " + transactionDate.toString());
        double amount = tdd.getAmount();
        String description = tdd.getDescription();
        Account fromAccount = this.getAccountByAccNumber(tdd.getFromAccountNumber());
        Transaction trans = new Transaction(transactionId, transactionType, transactionDate, amount, description, fromAccount, acc);
        Transaction obj = this.saveTransaction(trans);
        System.out.println("transaction saved successfully");
        return obj;
    }

    public Transaction fundTransferFunction(TransactionDepositDTO tdd) {
        int fromAccountNumber = tdd.getFromAccountNumber();
        int toAccountNumber = tdd.getAccountNumber();
        Account fromAcc = this.getAccountByAccNumber(fromAccountNumber);
        //System.out.println("Fund Transfer fromAcc=: " + fromAcc);
        Account toAcc = this.getAccountByAccNumber(toAccountNumber);
        //System.out.println("Fund Transfer toAcc=: " + toAcc);
        double oldOpeningBalFromAccout = fromAcc.getOpeningBalance();
        double newOpeningBalFromAcc = oldOpeningBalFromAccout - tdd.getAmount();
        //System.out.println("fund Transfer fromAccountNumber: " + fromAccountNumber + " newOpeningBalFromAcc=" + newOpeningBalFromAcc);
        this.updateOpeningBalanceByAccountNumber(fromAccountNumber, newOpeningBalFromAcc);
        int transactionId = this.getId("transaction", "transactionId");
        TransactionType transactionType = TransactionType.CREDIT;
        LocalDate transactionDate = LocalDate.now();
        //System.out.println("todays date is " + transactionDate.toString());
        String description = tdd.getDescription();
        Transaction trans = new Transaction(transactionId, transactionType, transactionDate, tdd.getAmount(), description, fromAcc, toAcc);
        this.saveTransaction(trans);
        transactionId = this.getId("transaction", "transactionId");
        transactionType = TransactionType.DEBIT;
        double oldOpeningBalToAcc = toAcc.getOpeningBalance();
        double newOpeningBalToAcc = oldOpeningBalToAcc + tdd.getAmount();
        this.updateOpeningBalanceByAccountNumber(toAccountNumber, newOpeningBalToAcc);
        trans = new Transaction(transactionId, transactionType, transactionDate, tdd.getAmount(), description, fromAcc, toAcc);
        Transaction obj = this.saveTransaction(trans);
        //System.out.println("transaction saved successfully");
        return obj;
    }

    public Transaction depositAmountInAccount(TransactionDepositDTO tdd) {
        int accountNumber = tdd.getAccountNumber();
        Account acc = this.getAccountByAccNumber(accountNumber);
        double newOpeningBalance = acc.getOpeningBalance() + tdd.getAmount();
        this.updateOpeningBalanceByAccountNumber(tdd.getAccountNumber(), newOpeningBalance);
        int transactionId = this.getId("transaction", "transactionId");
        TransactionType transactionType = TransactionType.CREDIT;
        LocalDate transactionDate = LocalDate.now();
        System.out.println("todays date is " + transactionDate.toString());
        double amount = tdd.getAmount();
        String description = tdd.getDescription();
        Account fromAccount = this.getAccountByAccNumber(tdd.getFromAccountNumber());
        Transaction trans = new Transaction(transactionId, transactionType, transactionDate, amount, description, fromAccount, acc);
        Transaction obj = this.saveTransaction(trans);
        System.out.println("transaction saved successfully");
        return obj;
    }

    public int updateOpeningBalanceByAccountNumber(int accountNumber, double newOpeningBalance) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Account a5=entityManager.find(Account.class, accountNumber);
            a5.setOpeningBalance(newOpeningBalance);
            entityManager.persist(a5);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return accountNumber;
        /*
        int res = 0;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("update account set openingBalance=? where accountNumber=?");
            stmt.setDouble(1, newOpeningBalance);
            stmt.setInt(2, accountNumber);
            res = stmt.executeUpdate();
            System.out.println("update balance is now " + newOpeningBalance + " accno is " + accountNumber);
            con.close();
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        return res;
        */

    }

    public List<Account> getAccountsByCustId(String strCustId) {
        List<Account> lst = new LinkedList();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            String query1 = "from Account a where a.customer.customerId=?1";
            Query q1=entityManager.createQuery(query1);
            q1.setParameter(1,Integer.valueOf(strCustId));
            lst=q1.getResultList();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return lst;

        /*
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from account where customerId=?");
            stmt.setString(1, strCustId);
            ResultSet rset = stmt.executeQuery();

            while(rset.next()) {
                int accountNumber = rset.getInt("accountNumber");
                Customer customer = this.getCustomerByCustId(strCustId);
                String strAccType=rset.getString("accountType");
                AccountType accountType = null;
                switch (strAccType) {
                    case "SALARY":
                        accountType = AccountType.SALARY;
                        break;
                    case "SAVINGS":
                        accountType = AccountType.SAVINGS;
                        break;
                    case "FD":
                        accountType = AccountType.FD;
                        break;
                    case "RD":
                        accountType = AccountType.RD;
                        break;
                    case "LOAN":
                        accountType = AccountType.LOAN;
                        break;
                    case "CURRENT":
                        accountType = AccountType.CURRENT;
                }

                double openingBalance = rset.getDouble("openingBalance");
                String strOpeningDate = rset.getString("openingDate");
                System.out.println(strOpeningDate);
                LocalDate openingDate = LocalDate.parse(strOpeningDate, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"));
                String description = rset.getString("description");
                Account acc = new Account(accountNumber, customer, accountType, openingBalance, openingDate, description);
                lst.add(acc);
            }

            con.close();
        } catch (SQLException var17) {
            var17.printStackTrace();
        }

        return lst;

         */
    }

    public Account getAccountByAccNumber(int accountNumber) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Account a1 = null;
        try {
            transaction.begin();
            a1 = entityManager.find(Account.class, accountNumber);

            if (a1 == null) {
                throw new EntityNotFoundException("Can't find account for ID "
                        + accountNumber);
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return a1;

        /*
        Account acc = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from account where accountNumber=?");
            stmt.setInt(1, accountNumber);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                int customerId = rset.getInt("customerId");
                String strAccType=rset.getString("accountType");
                AccountType accountType = null;
                switch (strAccType) {
                    case "SALARY":
                        accountType = AccountType.SALARY;
                        break;
                    case "SAVINGS":
                        accountType = AccountType.SAVINGS;
                        break;
                    case "FD":
                        accountType = AccountType.FD;
                        break;
                    case "RD":
                        accountType = AccountType.RD;
                        break;
                    case "LOAN":
                        accountType = AccountType.LOAN;
                        break;
                    case "CURRENT":
                        accountType = AccountType.CURRENT;
                }

                double openingBalance = rset.getDouble("openingBalance");
                String strOpeningDate = rset.getString("openingDate");
                System.out.println(strOpeningDate);
                LocalDate openingDate = LocalDate.parse(strOpeningDate, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"));
                String description = rset.getString("description");
                Customer customer = this.getCustomerByCustId(String.valueOf(customerId));
                acc = new Account(accountNumber, customer, accountType, openingBalance, openingDate, description);
            }

            con.close();
        } catch (SQLException var16) {
            var16.printStackTrace();
        }

        return acc;

         */
    }

    public Customer getCustomerByCustId(String strCustId) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("laasi");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Customer c1 = null;
        try {
            transaction.begin();
            c1 = entityManager.find(Customer.class, Integer.valueOf(strCustId));

            if (c1 == null) {
                throw new EntityNotFoundException("Can't find customer for ID "
                        + strCustId);
            }
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return c1;
        /*
        Customer cust = null;
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from customer where customerId=?");
            stmt.setString(1, strCustId);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                int customerId = rset.getInt("customerId");
                String firstName = rset.getString("firstName");
                String lastName = rset.getString("lastName");
                String emailId = rset.getString("emailid");
                String contactNo = rset.getString("contactNo");
                int addressId = rset.getInt("addressId");
                Address address = this.getAddressByAddressId(addressId);
                String strGender = rset.getString("gender");
                Gender gender;
                if (strGender.equals(Gender.MALE)) {
                    gender = Gender.MALE;
                } else {
                    gender = Gender.FEMALE;
                }

                String password = rset.getString("pwd");
                String confirmPassword = "";
                String strRegDate = rset.getString("registerationDate");
                System.out.println(strRegDate);
                LocalDate registerationDate = LocalDate.parse(strRegDate, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"));
                cust = new Customer(customerId, firstName, lastName, emailId, contactNo, address, gender, password, confirmPassword, registerationDate);
                System.out.println(cust);
            }

            con.close();
        } catch (SQLException var19) {
            var19.printStackTrace();
        }

        return cust;

         */
    }

    public List<Transaction> getTransactionsByAccountNumber(int accountNumber) {
        Connection con = this.getDBCon();

        try {
            PreparedStatement stmt = con.prepareStatement("select * from transaction where fromAccount=?");
            stmt.setInt(1, accountNumber);

            String var10;
            for(ResultSet rset = stmt.executeQuery(); rset.next(); var10 = rset.getString("description")) {
                int transactionId = rset.getInt("transactionId");
                double amount = rset.getDouble("amount");
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        }

        return null;
    }
}

