<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Utilisateurs</title>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/jquery.min.js"></script>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<ul class="nav nav-pills">
		<li><a href="<%=application.getContextPath()%>/admin/questionnaires">Gestion des questionnaires</a></li>
		<li class="active"><a href="<%=application.getContextPath()%>/admin/utilisateurs">Gestion des utilisateurs</a></li>
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/connexion/logout">Déconnexion</a></li>
	</ul>

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
				<td>Admin</td>
				<td colspan="4">Actif</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="users" items="${users}">
				<tr class="tr-user">
					<td>${users.id}</td>
					<td>${users.name}</td>
					<td>${users.mail}</td>
					<td>${users.password}</td>
					<td>${users.society}</td>
					<td>${users.phone}</td>
					<td>${users.registration}</td>
					<td>${users.admin}</td>
					<td>${users.active}</td>
					<td><a href="<%=application.getContextPath()%>/admin/utilisateurs/edit/${users.id}">Modifier</a></td>
					<td><a href="<%=application.getContextPath()%>/admin/utilisateurs/delete/${users.id}">Supprimer</a></td>
					<td><a href="<%=application.getContextPath()%>/admin/utilisateurs/infos/${users.id}">Infos</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button id="prev-user" class="btn btn-default">Précédent</button>
	<button id="next-user" class="btn btn-default">Suivant</button>
	
	<span class="error">${error}</span>
	
	<c:if test="${!empty ID && ACTION == 'edit'}">
		<div class="formulaire float-left">
			<div class="title">Edition utilisateur ${ID} :</div>
			<form class="form" id="form_user_edit" method="POST" action="<%=application.getContextPath()%>/admin/utilisateurs/edit/${ID}">
		
				<div class="form-group">
					<label for="name_edit">Nom :</label>
					<input class="form-control" type="text" id="name_edit" name="name_edit" value="${user.name}">
		        </div>
		        
		        <div class="form-group">
					<label for="mail_edit">Mail/Login :</label>
					<input class="form-control" type="text" id="mail_edit" name="mail_edit" value="${user.mail}">
		        </div>
		        
		         <div class="form-group">
					<label for="password_edit">Mot de passe :</label>
					<input class="form-control" type="text" id="password_edit" name="password_edit" value="${user.password}">
		        </div>
		        
		        <div class="form-group">
					<label for="society_edit">Société :</label>
					<input class="form-control" type="text" id="society_edit" name="society_edit" value="${user.society}">
		        </div>
		        
		        <div class="form-group">
					<label for="phone_edit">Téléphone :</label>
					<input class="form-control" type="text" id="phone_edit" name="phone_edit" value="${user.phone}">
		        </div>
		        
		        <div class="form-group">
					<label for="admin_edit">Admin :</label>
					<c:choose>
					    <c:when test="${user.admin == true}">
					        <input class="form-control" type="checkbox" id="admin_edit" name="admin_edit" checked="checked"/>
					    </c:when>    
					    <c:otherwise>
					    	<input class="form-control" type="checkbox" id="admin_edit" name="admin_edit">
					    </c:otherwise>
					</c:choose>
		        </div>
					
				<input class="btn btn-default" type="submit" value="Modifier"/>
			</form>
		</div>
	</c:if>
	
	<div class="formulaire float-right">
		<div class="title">Ajout utilisateur :</div>
		<form class="form" id="form_user_add" method="POST" action="<%=application.getContextPath()%>/admin/utilisateurs/add">
		
			<div class="form-group">
				<label for="name">Nom :</label>
				<input class="form-control" type="text" id="name" name="name">
	        </div>
	        
	        <div class="form-group">
				<label for="mail">Mail/Login :</label>
				<input class="form-control" type="text" id="mail" name="mail">
	        </div>
	        
	        <div class="form-group">
				<label for="society">Société :</label>
				<input class="form-control" type="text" id="society" name="society">
	        </div>
	        
	        <div class="form-group">
				<label for="phone">Téléphone :</label>
				<input class="form-control" type="text" id="phone" name="phone">
	        </div>
	        
	        <div class="form-group">
				<label for="admin">Admin :</label>
				<input class="form-control" type="checkbox" id="admin" name="admin">
	        </div>
				
			<input class="btn btn-default" type="submit" value="Ajouter"/>
		</form>
	</div>

	<script type="text/javascript">
	$(function() {
		  var items = $(".tr-user");
		  var numItems = items.length;
		  var perPage = 2;
		  var current = 0;
		  var numPage = (numItems / perPage) - 1;
		  
		  items.slice(perPage).hide();
		  
		  $('#prev-user').on('click', function(){
			  	if(current > 0)
				{
			  		current--;
			  		update();
				}
		  });
		  
		  $('#next-user').on('click', function(){
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