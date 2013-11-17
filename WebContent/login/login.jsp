<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Welcome</title>
</head>
<body>
	<h1>Welcome to Homework 5</h1>
	<p>Please log in.</p>
	<form action="LoginServlet" method="post">
    	User Name: <input type="text" name="name"/> <br/> <br/>
    	Password: <input type="text" name="password"/>
    	<input type="submit" value="Login"/>
	</form> <br/>
	<a href="create_new_account.jsp">Create New Account</a>
</body>
</html>