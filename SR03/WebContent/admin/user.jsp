<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Utilisateurs</title>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<ul class="nav nav-pills">
		<li><a href="<%=application.getContextPath()%>/admin/questionnaires">Gestion des questionnaires</a></li>
		<li><a href="<%=application.getContextPath()%>/admin/questions">Gestion des questions</a></li>
		<li><a href="<%=application.getContextPath()%>/admin/reponses">Gestion des réponses</a></li>
		<li class="active"><a href="<%=application.getContextPath()%>/admin/utilisateurs">Gestion des utilisateurs</a></li>
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/connexion/logout">Déconnexion</a></li>
	</ul>

	<span class="error">${error}</span>

	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Nom</td>
				<td>Mail</td>
				<td>Mot de passe</td>
				<td>Société</td>
				<td>Téléphone</td>
				<td>Date d'enregistrement</td>
				<td>Actif</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="surveys" items="${surveys}">
				<tr>
					<td>${surveys.id}</td>
					<td>${surveys.subject.name}</td>
					<td>${surveys.status}</td>
					<td>
						<a href="<%=application.getContextPath()%>/private/admin/questionnaires/edit/${surveys.id}">Modifier</a>
					</td>
					<td>
						<a href="<%=application.getContextPath()%>/private/admin/questionnaires/delete/${surveys.id}">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	
	<div class="formulaire">
		<form id="form_survey" method="POST" action="<%=application.getContextPath()%>/private/admin/questionnaires/add">
		
		<div class="form-group">
			<label for="selectSubject">Sujet :</label>
			<select class="form-control" id="selectSubject" name="selectSubject">
	            <c:forEach var="subjects" items="${subjects}">
	                <option value="${subjects.id}">${subjects.name}</option>
	            </c:forEach>
	        </select>
        </div>
		
		<div class="form-group">
			<label for="status">Actif :</label>        
			<input class="form-control" type="checkbox" id="status" name="status"/>
			<span class="erreur">${errors['status']}</span>
		</div>
			
			<input class="btn btn-default" type="submit" value="Ajouter"/>
		</form>
	</div>

</body>
</html>