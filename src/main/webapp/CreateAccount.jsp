<%@page import="model.Account"%>
<%@page import="model.AccountType"%>
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
<%
	Account acc= (Account)request.getAttribute("acc");
	if(acc!=null)
	{
		%>
		<h1>Account saved successfully</h1>
		<%
	}
	else
	{%>
		<h1>Account NOT saved </h1>
		<%
	}

%>
<%@ include file="subMenu.jsp" %>
<%
	DBHandler objDH=new DBHandler();
	int accountNumber=objDH.getId("Account", "accountNumber");
	String strEmailId=(String)session.getAttribute("ssnEmail");
	Customer cust=objDH.getCustomerByEmailidAndPwd(strEmailId);
%>
<div class="container">

<form class="row g-3" action="CreateAccount" method="post">
<div class="col-12">
    <label for="txtAccNo" class="form-label">Account Number</label>
    <input type="text" class="form-control" name="txtAccNo" value="<%=accountNumber%>" placeholder="Account Number" readonly=true>
 </div>
 <div class="col-12">
    <label for="txtCustId" class="form-label">Customer id</label>
    <input type="text" class="form-control" name="txtCustId" value="<%=cust.getCustomerId()%>" placeholder="Customer Id" readonly=true>
  </div>
    <div class="col-12">
    <label for="inputState" class="form-label">Account Type</label>
    <select id="inputState" name="txtAccountType" class="form-select">
      <option selected>Choose...</option>
      <%
      	for(AccountType at:AccountType.values())
      	{
      		%>
      		<option><%=at.name()%></option>
      		<%
      	}
      %>
      
      
    </select>
  </div>
  <div class="col-12">
    <label for="txtOpeningBalance" class="form-label">Opening Balcance</label>
    <input type="text" class="form-control" name="txtOpeningBalance">
  </div>
  <div class="col-md-12">
    <label for="txtDesc" class="form-label">Description</label>
    <input type="text" class="form-control" name="txtDesc">
  </div>
   <div class="col-12">
    <label  class="form-label">Opening Date</label>
    <input type="date" class="form-control" name="txtOpeningDate" placeholder="openingDate">
  </div>
  
  <div class="col-12">
    <button type="submit" class="btn btn-primary">Create Account</button>
  </div>
</form>
</div>
</body>
</html>