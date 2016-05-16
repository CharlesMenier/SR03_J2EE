<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Réponses</title>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<ul class="nav nav-pills">
		<li class="active"><a href="<%=application.getContextPath()%>/admin/questionnaires">Gestion des questionnaires</a></li>
		<li><a href="<%=application.getContextPath()%>/admin/utilisateurs">Gestion des utilisateurs</a></li>
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/connexion/logout">Déconnexion</a></li>
	</ul>
	
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Question</td>
				<td>Intitulé</td>
				<td>Number</td>
				<td>Bonne réponse</td>
				<td colspan="3">Actif</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="answers" items="${answers}">
				<tr>
					<td>${answers.id}</td>
					<td>${answers.question.id}</td>
					<td>${answers.label}</td>
					<td>${answers.number}</td>
					<td>${answers.correct}</td>
					<td>${answers.status}</td>
					<td><a href="<%=application.getContextPath()%>/admin/reponses/edit/${answers.id}">Modifier</a></td>
					<td><a href="<%=application.getContextPath()%>/admin/reponses/delete/${answers.id}">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${!empty ID && action == 'edit'}">
		<div class="formulaire float-left">
			<div class="title">Edition réponse ${ID} :</div>
			<form class="form" id="form_answer_edit" method="POST" action="<%=application.getContextPath()%>/admin/reponses/edit/${ID}">
			
				<div class="form-group hidden">
					<input type="hidden" id="question_edit" name="question_edit" value="${QUESTION}" />
		        </div>
		        
		        <div class="form-group">
					<label for="label_edit">Intitulé :</label>
					<input class="form-control" type="text" id="label_edit" name="label_edit" value="${answer.label}" />
		        </div>
		        
		        <div class="form-group">
					<label for="number_edit">Numéro :</label>
					<input class="form-control" type="text" id="number_edit" name="number_edit" value="${answer.number}" />
		        </div>
		        
		        <div class="form-group">
					<label for="correct_edit">Bonne réponse :</label>
					<c:choose>
					    <c:when test="${answer.status == true}">
					        <input class="form-control" type="checkbox" id="correct_edit" name="correct_edit" checked="checked"/>
					    </c:when>    
					    <c:otherwise>
					    	<input class="form-control" type="checkbox" id="correct_edit" name="correct_edit"/>
					    </c:otherwise>
					</c:choose>
				</div>
				
				<div class="form-group">
					<label for="status_edit">Actif :</label>
					<c:choose>
					    <c:when test="${question.status == true}">
					        <input class="form-control" type="checkbox" id="status_edit" name="status_edit" checked="checked"/>
					    </c:when>    
					    <c:otherwise>
					    	<input class="form-control" type="checkbox" id="status_edit" name="status_edit"/>
					    </c:otherwise>
					</c:choose>
					<span class="erreur">${errors['status']}</span>
				</div>
				
				<input class="btn btn-default" type="submit" value="Modifier"/>
				
			</form>
		</div>
	</c:if>
	
	<div class="formulaire float-right">
		<div class="title">Ajout réponse :</div>
		<form class="form" id="form_answer" method="POST" action="<%=application.getContextPath()%>/admin/reponses/add">
		
			<div class="form-group hidden">
				<input type="hidden" id="question" name="question" value="${ID}" />
	        </div>
	        
	        <div class="form-group">
				<label for="label">Intitulé :</label>
				<input class="form-control" type="text" id="label" name="label"/>
	        </div>
	        
	        <div class="form-group">
				<label for="number">Numéro :</label>
				<input class="form-control" type="text" id="number" name="number"/>
	        </div>
	        
	        <div class="form-group">
				<label for="correct">Bonne réponse :</label>        
				<input class="form-control" type="checkbox" id="correct" name="correct"/>
			</div>
			
			<div class="form-group">
				<label for="status">Actif :</label>        
				<input class="form-control" type="checkbox" id="status" name="status"/>
			</div>
			
			<input class="btn btn-default" type="submit" value="Ajouter"/>
		</form>
	</div>
	
	<div class="error">${error}</div>
	
	<a href="<%=application.getContextPath()%>/admin/questions/show/${SURVEY}" class="btn btn-default">Retour</a>

</body>
</html>