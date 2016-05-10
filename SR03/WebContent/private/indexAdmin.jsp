<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<ul class="nav nav-pills">
		<li role="presentation" class="active"><a href="<%=application.getContextPath()%>/connexion">Accueil</a></li>
		<li role="presentation"><a href="<%=application.getContextPath()%>/private/admin/questionnaires">Gestion des questionnaires</a></li>
		<li role="presentation"><a href="<%=application.getContextPath()%>/private/admin/questions">Gestion des questions</a></li>
		<li role="presentation"><a href="<%=application.getContextPath()%>/private/admin/reponses">Gestion des réponses</a></li>
		<li role="presentation"><a href="<%=application.getContextPath()%>/private/admin/utilisateurs">Gestion des utilisateurs</a></li>
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/deconnexion">Déconnexion</a></li>
	</ul>
	
	

</body>
</html>