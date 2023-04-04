package controller;

import dbHandler.DBHandler;
import dto.TransactionDepositDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Transaction;

import java.io.IOException;

@WebServlet(name = "DepositInAcc", value = "/DepositInAcc")
public class DepositInAcc extends HttpServlet {
    public DepositInAcc() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("txtCustid"));
        int accountNumber = Integer.parseInt(request.getParameter("selAccountNo"));
        double amount = Double.parseDouble(request.getParameter("txtAmount"));
        String description = request.getParameter("txtDesc");
        int fromAccountNumber = Integer.parseInt(request.getParameter("selfromAcc"));
        TransactionDepositDTO tdd = new TransactionDepositDTO(customerId, accountNumber, amount, description, fromAccountNumber);
        DBHandler objDH = new DBHandler();
        Transaction trans = objDH.depositAmountInAccount(tdd);
        request.setAttribute("trans", trans);
        RequestDispatcher rd = request.getRequestDispatcher("DepositInAcc.jsp");
        rd.forward(request, response);
    }
}
