
<%@page import="model.Transaction"%>
<%@page import="model.Account"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
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

<%@ include file="subMenu.jsp" %>
<%

	Transaction trans=(Transaction)request.getAttribute("trans");
	if(trans==null)
	{
		%>
		<!-- <h1>transacton not commited</h1> -->
		<%
	}
	else
	{
		%>
		<h1>Transaction commited successfully!</h1>
		<%
	}

	DBHandler objDH=new DBHandler();
	
	String strEmailId=(String)session.getAttribute("ssnEmail");
	
	Customer cust=objDH.getCustomerByEmailidAndPwd(strEmailId);

	List<Account> lst=objDH.getAccountsByCustId(String.valueOf(cust.getCustomerId()));
%>
<div class="container">
<h1>Deposit in Account</h1>
<form class="row g-3" action="DepositInAcc" method="post">
  <div class="col-12">
    <label for="txtCustid" class="form-label">Customer ID</label>
    <input type="text" class="form-control" name="txtCustid" value="<%=cust.getCustomerId()%>" placeholder="Customer Id" readonly>
  </div>
    <div class="col-md-6">
    <label for="selAccountNo" class="form-label">Select TO Account Number</label>
    <select id="selAccountNo" name="selAccountNo" class="form-select">
      <option selected>Choose...</option>
      <%
      	for(Account acc:lst)
      	{
      		%>
      		<option><%=acc.getAccountNumber()%></option>
      		<%
      	}
      %>
      
      
    </select>
  </div>
  <!-- <div class="col-md-6">
    <label for="txtAccType" class="form-label">Account Type</label>
    <input type="text" class="form-control" name="txtAccType" readonly>
  </div>
  <div class="col-md-6">
    <label for="txtOpeningBalance" class="form-label">Opening Balance</label>
    <input type="text" class="form-control" name="txtOpeningBalance" readonly>
  </div> -->
  <div class="col-md-6">
    <label for="txtAmount" class="form-label">amount</label>
    <input type="text" class="form-control" name="txtAmount">
  </div>
  <div class="col-md-6">
    <label for="txtDesc" class="form-label">Description</label>
    <input type="text" class="form-control" name="txtDesc">
  </div>
    
  <div class="col-md-6">
    <label for="selfromAcc" class="form-label">From Account</label>
    <select id="selfromAcc" name="selfromAcc" class="form-select">
      <option selected>Choose...</option>
       <%
      	for(Account acc:lst)
      	{
      		%>
      		<option><%=acc.getAccountNumber()%></option>
      		<%
      	}
      %>
    </select>
  </div>
    
  <div class="col-12">
    <button type="submit" class="btn btn-primary">Deposit</button>
  </div>
</form>

</div>
</body>
</html>