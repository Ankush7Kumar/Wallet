package controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;


import dbHandler.DBHandler;
import model.Customer;

@WebServlet(name = "Auth", value = "/Auth")
public class Auth extends HttpServlet {

    public Auth() {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strEmail = request.getParameter("txtEmail");
        String strPwd = request.getParameter("txtPwd");
        DBHandler objDH = new DBHandler();
        boolean res = objDH.isValidCustByEmailidAndPwd(strEmail, strPwd);
        if (res) {
            HttpSession session = request.getSession();
            session.setAttribute("ssnEmail", strEmail);
            Customer cust = objDH.getCustomerByEmailidAndPwd(strEmail);
            session.setAttribute("ssnCustId", cust.getCustomerId());
            //System.out.println("pppppppppppp " + cust);
            response.sendRedirect(request.getContextPath() + "/WelcomePage.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/ErrorPage.jsp");
        }



    }
}
