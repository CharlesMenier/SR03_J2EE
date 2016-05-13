<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions</title>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<ul class="nav nav-pills">
		<li><a href="<%=application.getContextPath()%>/admin/questionnaires">Gestion des questionnaires</a></li>
		<li class="active"><a href="<%=application.getContextPath()%>/admin/questions">Gestion des questions</a></li>
		<li><a href="<%=application.getContextPath()%>/admin/reponses">Gestion des réponses</a></li>
		<li><a href="<%=application.getContextPath()%>/admin/utilisateurs">Gestion des utilisateurs</a></li>
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/connexion/logout">Déconnexion</a></li>
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
			<c:forEach var="questions" items="${questions}">
				<tr>
					<td>${questions.id}</td>
					<td>${questions.subject.name}</td>
					<td>${questions.status}</td>
					<td>
						<a href="<%=application.getContextPath()%>/admin/questions/edit/${questions.id}">Modifier</a>
					</td>
					<td>
						<a href="<%=application.getContextPath()%>/admin/questions/delete/${questions.id}">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${!empty ID}">
		<div class="formulaire">
			<form id="form_survey" method="POST" action="<%=application.getContextPath()%>/admin/questionnaires/edit/${ID}">
			
			<div class="form-group">
				<label for="selectSubject">Sujet :</label>
				<select class="form-control" id="selectSubject" name="selectSubject">
		            <c:forEach var="subjects" items="${subjects}">
			            <c:choose>
						    <c:when test="${subjects.id == survey.subject.id}">
						        <option value="${subjects.id}" selected="true">${subjects.name}</option>
						    </c:when>    
						    <c:otherwise>
						    	<option value="${subjects.id}">${subjects.name}</option>
						    </c:otherwise>
						</c:choose>
		            </c:forEach>
		        </select>
	        </div>
			
			<div class="form-group">
				<label for="status">Actif :</label>
				<c:choose>
				    <c:when test="${survey.status == true}">
				        <input class="form-control" type="checkbox" id="status" name="status" checked="checked"/>
				    </c:when>    
				    <c:otherwise>
				    	<input class="form-control" type="checkbox" id="status" name="status"/>
				    </c:otherwise>
				</c:choose>
				
				<span class="erreur">${errors['status']}</span>
			</div>
				
				<input class="btn btn-default" type="submit" value="Modifier"/>
			</form>
		</div>
	</c:if>
	
	
	
	<div class="formulaire">
		<form id="form_question" method="POST" action="<%=application.getContextPath()%>/admin/questions/add">
		
		<div class="form-group">
			<label for="selectSubject">Questionnaire :</label>
			<select class="form-control" id="selectSurvey" name="selectSurvey">
	            <c:forEach var="subjects" items="${subjects}">
	                <option value="${subjects.id}">${subjects.name}</option>
	            </c:forEach>
	        </select>
        </div>
        
        <div class="form-group">
			<label for="label">Label :</label>
			<input class="form-control" type="text" id="label" name="label"/>
        </div>
        
        <div class="form-group">
			<label for="number">Numéro :</label>
			<input class="form-control" type="text" id="number" name="number"/>
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