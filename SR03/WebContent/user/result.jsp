<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
<title>Resultat</title>
</head>
<body>
<ul class="list-group">
  <li class="list-group-item">Nom : ${userName}</li>
  <li class="list-group-item">Questionnaire ID : ${survey.id}</li>
  <li class="list-group-item">Questionnaire sujet : ${survey.subject.name }</li>
  <li class="list-group-item">Note : ${score }</li>
  <li class="list-group-item">Duree : ${time }</li>
</ul>

<div class="btn-group btn-group-justified">
  <a href="<%=application.getContextPath()%>/user/user.jsp" class="btn btn-primary">Retourner</a>
  <a href="<%=application.getContextPath()%>/connexion/logout" class="btn btn-primary">Logout</a>
</div>
</body>
</html>