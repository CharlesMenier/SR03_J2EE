<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Infos utilisateur</title>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/jquery.min.js"></script>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<table class="table table-bordered">
		<tr><td>ID</td><td>${user.id}</td></tr>
		<tr><td>Nom</td><td>${user.name}</td></tr>
		<tr><td>Mail</td><td>${user.mail}</td></tr>
		<tr><td>Mot de passe</td><td>${user.password}</td></tr>
		<tr><td>Société</td><td>${user.society}</td></tr>
		<tr><td>Téléphone</td><td>${user.phone}</td></tr>
		<tr><td>Date d'enregistrement</td><td>${user.registration}</td></tr>
		<tr><td>Admin</td><td>${user.admin}</td></tr>
		<tr><td>Actif</td><td>${user.active}</td></tr>
	</table>
	
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Sujet</td>
				<td>Score</td>
				<td>Temps</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="courses" items="${courses}">
				<tr class="tr-course">
					<td>${courses.id}</td>
					<td>${courses.survey.subject.name}</td>
					<td>${courses.score}</td>
					<td>${courses.time}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="<%=application.getContextPath()%>/admin/utilisateurs" class="btn btn-default">Retour</a>

</body>
</html>