<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
<title>Utilisateur</title>
</head>
<body>
<ul class="nav nav-pills">
	<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/connexion/logout">Déconnexion</a></li>
</ul>

<h1><span class="label label-info">Information</span></h1>
<div>
	<ul class="list-group">
	  <li class="list-group-item"><span class="label label-default">Username : </span>${sessionUser.name}</li>
	  <li class="list-group-item"><span class="label label-default">Email : </span>${sessionUser.mail}</li>
	  <li class="list-group-item"><span class="label label-default">Societe : </span>${sessionUser.society}</li>
	  <li class="list-group-item"><span class="label label-default">Tel : </span>${sessionUser.phone }</li>
	  <li class="list-group-item"><span class="label label-default">Date : </span>${sessionUser.registration }</li>
	</ul>
</div>

<div class="btn-group btn-group-justified">
  <a href="<%=application.getContextPath()%>/user/survey" class="btn btn-primary">Liste de questionnaires</a>
  <a href="<%=application.getContextPath()%>/user/survey/history" class="btn btn-primary">Histoire</a>
</div>

</body>
</html>