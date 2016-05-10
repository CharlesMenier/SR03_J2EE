<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questionnaires</title>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<ul class="nav nav-pills">
		<li role="presentation"><a href="<%=application.getContextPath()%>/connexion">Accueil</a></li>
		<li role="presentation" class="active"><a href="<%=application.getContextPath()%>/private/admin/questionnaires">Gestion des questionnaires</a></li>
		<li role="presentation"><a href="<%=application.getContextPath()%>/private/admin/questions">Gestion des questions</a></li>
		<li role="presentation"><a href="<%=application.getContextPath()%>/private/admin/reponses">Gestion des réponses</a></li>
		<li role="presentation"><a href="<%=application.getContextPath()%>/private/admin/utilisateurs">Gestion des utilisateurs</a></li>
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/deconnexion">Déconnexion</a></li>
	</ul>

	<span class="error">${error}</span>

	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Sujet</td>
				<td colspan="3">Actif</td>
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