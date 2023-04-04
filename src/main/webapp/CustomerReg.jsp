<%@page import="model.Customer"%>
<%@page import="dbHandler.DBHandler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Menu.jsp" %>
<%
DBHandler objDH=new DBHandler();
	int custId=objDH.getId("customer", "customerId");

%>
Customer registeration form
<div class="container">
<%
	Customer obj=(Customer)request.getAttribute("cust");
	
	if(obj!=null)
	{
		%>
		<h1>Customer registered successfully</h1>
		<%
	}
	
%>
<form class="row g-3" action="CustomerReg" method="post">
<div class="col-12">
    <label for="txtCustid" class="form-label">Customer ID</label>
    <input type="text" class="form-control" name="txtCustid" value="<%=custId %>" placeholder="Customer Id" readonly=true>
  </div>
  <div class="col-md-6">
    <label for="txtFnm" class="form-label">First Name</label>
    <input type="text" class="form-control" name="txtFnm">
  </div>
  <div class="col-md-6">
    <label for="txtLnm" class="form-label">Last Name</label>
    <input type="text" class="form-control" name="txtLnm">
  </div>
  <div class="col-md-6">
    <label for="inputEmail4" class="form-label">email</label>
    <input type="email" class="form-control" name="txtEmailId">
  </div>
  <div class="col-md-6">
    <label for="inputPassword4" class="form-label">ContactNo</label>
    <input type="text" class="form-control" name="txtContactNo">
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Address Line 1</label>
    <input type="text" class="form-control" name="txtAddressLine1" placeholder="1234 Main St">
  </div>
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Address Line 2</label>
    <input type="text" class="form-control" name="txtAddressLine2" placeholder="Apartment, studio, or floor">
  </div>
  
  <div class="col-md-6">
    <label for="inputCity" class="form-label">City</label>
    <input type="text" class="form-control" name="txtCity">
  </div>
  <div class="col-md-4">
    <label for="inputState" class="form-label">State</label>
    <select id="inputState" name="txtState" class="form-select">
      <option selected>Choose...</option>
      <option>Haryana</option>
      <option>Punjab</option>
      <option>Maharahstra</option>
    </select>
  </div>
  <div class="col-md-2">
    <label for="inputZip" class="form-label">Pincode</label>
    <input type="text" class="form-control" name="txtPincode">
  </div>
   <div class="col-md-6">
    <label  class="form-label">Password</label>
    <input type="password" class="form-control" name="txtPassword" placeholder="password">
  </div>
  <div class="col-md-6">
    <label  class="form-label">Registeration Date</label>
    <input type="date" class="form-control" name="txtRegDate" placeholder="reg date">
  </div>
    <div class="col-md-6">
    <label for="inputCity" class="form-label">Gender</label>
       <input class="form-check-input" type="radio" name="rdbGender" value="Male">
        <label class="form-check-label" for="gridRadios2">
          Male
        </label>
  </div>
    <div class="col-md-6">
    
       <input class="form-check-input" type="radio" name="rdbGender" value="Female">
        <label class="form-check-label" for="gridRadios2">
          Female
        </label>
  </div>
  
  <div class="col-12">
    <button type="submit" class="btn btn-primary">Sign in</button>
  </div>
</form>
</div>
</body>
</html>