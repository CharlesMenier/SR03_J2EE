<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions</title>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/jquery.min.js"></script>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<ul class="nav nav-pills">
		<li class="active"><a href="<%=application.getContextPath()%>/admin/questionnaires">Gestion des questionnaires</a></li>
		<li><a href="<%=application.getContextPath()%>/admin/utilisateurs">Gestion des utilisateurs</a></li>
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/connexion/logout">D�connexion</a></li>
	</ul>
	
	<span class="error">${error}</span>

	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Questionnaire</td>
				<td>Intitul�</td>
				<td>Number</td>
				<td colspan="3">Actif</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="questions" items="${questions}">
				<tr class="tr-question">
					<td>${questions.id}</td>
					<td>${questions.survey.id}</td>
					<td>${questions.label}</td>
					<td>${questions.number}</td>
					<td>${questions.status}</td>
					<td>
						<a href="<%=application.getContextPath()%>/admin/questions/edit/${questions.id}">Modifier</a>
						<br/>
						<a href="<%=application.getContextPath()%>/admin/reponses/show/${questions.id}">Modifier r�ponses</a>
					</td>
					<td><a href="<%=application.getContextPath()%>/admin/questions/delete/${questions.id}">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button id="prev-question" class="btn btn-default">Pr�c�dent</button>
	<button id="next-question" class="btn btn-default">Suivant</button>
	
	<c:if test="${!empty ID && ACTION == 'edit'}">
		<div class="formulaire float-left">
			<div class="title">Edition question ${ID} :</div>
			<form class="form" id="form_question_edit" method="POST" action="<%=application.getContextPath()%>/admin/questions/edit/${ID}">
			
				<div class="form-group hidden">
					<input type="hidden" id="survey_edit" name="survey_edit" value="${SURVEY}" />
		        </div>
		        
		        <div class="form-group">
					<label for="label_edit">Intitul� :</label>
					<input class="form-control" type="text" id="label_edit" name="label_edit" value="${question.label}" />
		        </div>
		        
		        <div class="form-group">
					<label for="number_edit">Num�ro :</label>
					<input class="form-control" type="text" id="number_edit" name="number_edit" value="${question.number}" />
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
		<div class="title">Ajout question :</div>
		<form class="form" id="form_question" method="POST" action="<%=application.getContextPath()%>/admin/questions/add">
		
			<div class="form-group hidden">
				<c:choose>
				    <c:when test="${!empty SURVEY}">
				        <input type="hidden" id="survey" name="survey" value="${SURVEY}" />
				    </c:when>    
				    <c:otherwise>
				    	<input type="hidden" id="survey" name="survey" value="${ID}" />
				    </c:otherwise>
				</c:choose>
	        </div>
	        
	        <div class="form-group">
				<label for="label">Intitul� :</label>
				<input class="form-control" type="text" id="label" name="label"/>
	        </div>
	        
	        <div class="form-group">
				<label for="number">Num�ro :</label>
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

	<a href="<%=application.getContextPath()%>/admin/questionnaires" class="btn btn-default">Retour</a>
	
	<script type="text/javascript">
	$(function() {
		  var items = $(".tr-question");
		  var numItems = items.length;
		  var perPage = 2;
		  var current = 0;
		  var numPage = (numItems / perPage) - 1;
		  
		  items.slice(perPage).hide();
		  
		  $('#prev-question').on('click', function(){
			  	if(current > 0)
				{
			  		current--;
			  		update();
				}
		  });
		  
		  $('#next-question').on('click', function(){
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