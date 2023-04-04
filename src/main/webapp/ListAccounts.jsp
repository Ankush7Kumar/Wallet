<%@page import="model.Account"%>
<%@page import="java.util.List"%>
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
<%@ include file='subMenu.jsp' %>

<%
	DBHandler objDH=new DBHandler();
	String strCustId=String.valueOf( session.getAttribute("ssnCustId"));
	List<Account> lst=objDH.getAccountsByCustId(strCustId);
	
%>
<div class="container">

<table class="table table-sm">
    <thead>
    <tr>
      <th scope="col">Account Number</th>
      <th scope="col">Cust Id</th>
	  <th scope="col">Cust Name</th>
      <th scope="col">Account Type</th>
      <th scope="col">Opening Balance</th>
      <th scope="col">Description</th>
      <th scope="col">Opening Date</th>
    </tr>
  </thead>
  <%
  for(Account acc:lst)
  {
	  %>
	<tr>
	  <td><%=acc.getAccountNumber()%></td>
	  <td><%=acc.getCustomer().getCustomerId() %></td>
  	  <td><%=acc.getCustomer().getFirstName() %></td>
 	  <td><%=acc.getAccountType()%></td>
 	  <td><%=acc.getOpeningBalance()%></td>
 	  <td><%=acc.getDescription()%></td>
   	  <td><%=acc.getOpeningDate()%></td>
  </tr>  
	  <%
  }
  %>
  
</table>
</div>
</body>
</html>