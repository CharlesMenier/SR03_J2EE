<%@page import="model.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="true" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questionnaires</title>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/jquery.min.js"></script>
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
				<td>Sujet</td>
				<td colspan="3">Actif</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="surveys" items="${surveys}">
				<tr class="tr-survey">
					<td>${surveys.id}</td>
					<td>${surveys.subject.name}</td>
					<td>${surveys.status}</td>
					<td>
						<a href="<%=application.getContextPath()%>/admin/questionnaires/edit/${surveys.id}">Modifier</a>
						<br/>
						<a href="<%=application.getContextPath()%>/admin/questions/show/${surveys.id}">Modifier questions</a>
					</td>
					<td><a href="<%=application.getContextPath()%>/admin/questionnaires/delete/${surveys.id}">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button id="prev-survey" class="btn btn-default">Précédent</button>
	<button id="next-survey" class="btn btn-default">Suivant</button>
	
	<span class="error">${error}</span>
	
	<c:if test="${!empty ID}">
		<div class="formulaire float-left">
			<div class="title">Edition questionnaire ${ID} :</div>
			<form class="form" id="form_survey_edit" method="POST" action="<%=application.getContextPath()%>/admin/questionnaires/edit/${ID}">
			
			<div class="form-group">
				<label for="selectSubject">Sujet :</label>
				<select class="form-control" id="selectSubject_edit" name="selectSubject_edit">
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
		<div class="title">Ajout questionnaire :</div>
		<form class="form" id="form_survey" method="POST" action="<%=application.getContextPath()%>/admin/questionnaires/add">
		
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
	
	<script type="text/javascript">
	$(function() {
		  var items = $(".tr-survey");
		  var numItems = items.length;
		  var perPage = 2;
		  var current = 0;
		  var numPage = (numItems / perPage) - 1;
		  
		  items.slice(perPage).hide();
		  
		  $('#prev-survey').on('click', function(){
			  	if(current > 0)
				{
			  		current--;
			  		update();
				}
		  });
		  
		  $('#next-survey').on('click', function(){
			  	if(current < numPage)
				{
			  		current++;
			  		update();
				}
		  });
		  
		  function update()
		  {
			  var showFrom = perPage * (current);
		      var showTo = showFrom + perPage;
		      items.hide();
		      items.slice(showFrom, showTo).show();
		  }
		});
	</script>
	
</body>
</html>