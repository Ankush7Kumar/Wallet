package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import dbHandler.DBHandler;
import model.Address;
import model.AccountType;
import model.Customer;
import model.Gender;
import java.io.IOException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CustomerReg", value = "/CustomerReg")
public class CustomerReg extends HttpServlet {
    public CustomerReg() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("txtCustid"));
        String firstName = request.getParameter("txtFnm");
        String lastName = request.getParameter("txtLnm");
        String emailId = request.getParameter("txtEmailId");
        String contactNo = request.getParameter("txtContactNo");
        DBHandler objDH = new DBHandler();
        int addressId = objDH.getId("Address", "addressId");
        String addressLine1 = request.getParameter("txtAddressLine1");
        String addressLine2 = request.getParameter("txtAddressLine2");
        String city = request.getParameter("txtCity");
        String state = request.getParameter("txtState");
        String pincode = request.getParameter("txtPincode");
        String strGender = request.getParameter("rdbGender");
        Gender gender;
        if (strGender.equals("Male")) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        String password = request.getParameter("txtPassword");
        String confirmPassword = "";
        String strRegDate = request.getParameter("txtRegDate");
        LocalDate registerationDate = LocalDate.parse(strRegDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Address address = new Address(addressId, addressLine1, addressLine2, city, state, pincode);
        System.out.println("cp1");
        address = objDH.saveAddress(address);
        //System.out.println("cp2 address"+toret);
        System.out.println("cp3");


        Customer cust = new Customer(customerId, firstName, lastName, emailId, contactNo, address, gender, password, confirmPassword, registerationDate);
        System.out.println("cp4");
        cust = objDH.saveCustomer(cust);
        System.out.println("cp5");
        request.setAttribute("cust", cust);
        System.out.println("cp6");
        RequestDispatcher rd = request.getRequestDispatcher("/CustomerReg.jsp");
        rd.forward(request, response);
    }
}
