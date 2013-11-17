<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Create Account</title>
</head>
<body>
	<h1>Create New Account</h1>
	<p>Please enter proposed name and password.</p>
	<form action="AccountServlet" method="post">
    	User Name: <input type="text" name="name"/> <br/> <br/>
    	Password: <input type="text" name="password"/>
    	<input type="submit" value="Login"/>
	</form>
</body>
</html>