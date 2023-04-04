package controller;

import dbHandler.DBHandler;
import dto.TransactionDepositDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Transaction;

import java.io.IOException;

@WebServlet(name = "FundTransfer", value = "/FundTransfer")
public class FundTransfer extends HttpServlet {
    public FundTransfer() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("txtCustid"));
        int toAaccountNumber = Integer.parseInt(request.getParameter("selAccountNo"));
        double amount = Double.parseDouble(request.getParameter("txtAmount"));
        String description = request.getParameter("txtDesc");
        int fromAccountNumber = Integer.parseInt(request.getParameter("selfromAcc"));
        TransactionDepositDTO tdd = new TransactionDepositDTO(customerId, toAaccountNumber, amount, description, fromAccountNumber);
        System.out.println("fund transfer tdd= : " + tdd);
        DBHandler objDH = new DBHandler();
        Transaction trans = objDH.fundTransferFunction(tdd);
        request.setAttribute("trans", trans);
        RequestDispatcher rd = request.getRequestDispatcher("FundTransfer.jsp");
        rd.forward(request, response);
    }
}
