package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import dbHandler.DBHandler;
import model.Account;
import model.AccountType;
import model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CreateAccount", value = "/CreateAccount")
public class CreateAccount extends HttpServlet {
    public CreateAccount() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int accountNumber = Integer.parseInt(request.getParameter("txtAccNo"));
        int custId = Integer.parseInt(request.getParameter("txtCustId"));
        DBHandler objDH = new DBHandler();
        Customer customer = objDH.getCustomerByCustId(String.valueOf(custId));
        AccountType accountType = null;
        String strAccType=request.getParameter("txtAccountType");
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

        System.out.println(strAccType + " " + accountType.name());
        double openingBalance = Double.parseDouble(request.getParameter("txtOpeningBalance"));
        String strOpeningDate = request.getParameter("txtOpeningDate");
        LocalDate openingDate = LocalDate.parse(strOpeningDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String description = request.getParameter("txtDesc");
        Account account = new Account(accountNumber, customer, accountType, openingBalance, openingDate, description);
        Account retObj = objDH.saveAccount(account);
        if (retObj == null) {
            out.println("account not saved ");
        } else {
            out.println("account saved successfully");
        }

        request.setAttribute("acc", retObj);
        RequestDispatcher rd = request.getRequestDispatcher("CreateAccount.jsp");
        rd.forward(request, response);
    }
}
