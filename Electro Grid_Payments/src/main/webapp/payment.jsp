<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payments</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/users.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Payments</h1>
<form id="formUser" name="formUser">
 UserName:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Card_No:
 <input id="contact" name="contact" type="text"
 class="form-control form-control-sm">
 <br>  CVC:
 <input id="email" name="email" type="text"
 class="form-control form-control-sm">
 <br> Exp_Date:
 <input id="password" name="password" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
 <input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divIUsersGrid">
 <%
 Payment paymentObj = new Payment();
 out.print(paymentObj.readUsers());
 %>
</div>
</div> </div> </div>
</body>
</html>