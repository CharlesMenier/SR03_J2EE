<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>

	<ul>
		<li><a href="<%=application.getContextPath()%>/private/surveys">Gestion des questionnaires</a></li>
		<li><a href="<%=application.getContextPath()%>/private/users">Gestion des utilisateurs</a></li>
	</ul>
	
	<a href="<%=application.getContextPath()%>/deconnexion">Déconnexion</a>

</body>
</html>