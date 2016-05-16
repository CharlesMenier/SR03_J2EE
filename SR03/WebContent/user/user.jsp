<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Utilisateur</title>
</head>
<body>
<h3>User Information</h3>
<div>
	<p>Username : ${sessionUser.name}</p>
	<p>Email : ${sessionUser.mail}</p>
	<p>Society : ${sessionUser.society}</p>
	<p>Phone : ${sessionUser.phone }</p>
	<p>Register Date : ${sessionUser.registration }</p>
</div>

<div>
	<a href="<%=application.getContextPath()%>/user/survey" id="ManageSurvey">Liste de questionnaires</a>
</div>
</body>
</html>