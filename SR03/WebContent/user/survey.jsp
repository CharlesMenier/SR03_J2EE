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
		<li><a id="disconnect" class="btn btn-warning" href="<%=application.getContextPath()%>/connexion/logout">Déconnexion</a></li>
	</ul>

	<div class="formulaire float-left">
		<div class="title">Chercher questionnaires :</div>
		<form class="form" id="form_survey" method="POST" action="<%=application.getContextPath()%>/user/survey/search">
		
		<div class="form-group">
			<label for="selectSubject">Sujet :</label>
			<select class="form-control" id="selectSubject" name="selectSubject">
				<option value="default" ></option>
	            <c:forEach var="subject" items="${subjects}">
	            	<fmt:parseNumber var="subejctId" type="number" value="${selectedSubject}" />
	            	<c:choose>
	            		<c:when test="${subjectId eq subject.id }">
	            			<option value="${subject.id }" selected>${subject.name }</option>
	          	  		</c:when>
	          	  		<c:otherwise>
	          	  			<option value="${subject.id }">${subject.name }</option>
	          	  		</c:otherwise>
	          	  	</c:choose>
	            </c:forEach>
	        </select>
        </div>
		<input class="btn btn-default" type="submit" value="Chercher"/>
		</form>
	</div>
	
	<span class="error">${error}</span>

	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Sujet</td>
				<td>Status</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="survey" items="${surveys}">
				<tr>
					<td>${survey.id}</td>
					<td>${survey.subject.name}</td>
					<td>
						<c:choose>
							<c:when test="${survey.status}">
								<a href="<%=application.getContextPath()%>/user/survey/answer/${survey.id}">Commencer</a>
							</c:when>
							<c:otherwise>Pas disponible</c:otherwise>
						
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<ul class="pagination">
		<%
			int pageNumber = (int) request.getAttribute("pageNum");
			int currentPage = (int) request.getAttribute("currentPage");
			String selectedSubject = (String) request.getAttribute("selectedSubject");
			System.out.println("currentPage : " +currentPage);
			System.out.println("pageNumber : " + String.valueOf(pageNumber));
			String url = "";
			if (selectedSubject.equals("default")) {
				url = request.getContextPath() + "/user/survey?curPage=";
			} else {
				url = request.getContentType() + "/user/survey/search/" + selectedSubject + "?curPage=";
			}
			for (int i=1; i<=pageNumber; i++) {
				if (i == currentPage) {
					out.println("<li class=\"active\"><a = href=\"" + url + String.valueOf(i) + "\">" + 
						String.valueOf(currentPage) + "</a></li>");
				} else {
					out.println("<li><a = href=\"" + url + String.valueOf(i) + "\">" + 
							String.valueOf(currentPage) + "</a></li>");	
				}
			}
		%>
	</ul>
	<br/>
	
	<a href="<%=application.getContextPath()%>/user/user.jsp" class="btn btn-success" role="button">Retourner</a>
	
</body>
</html>