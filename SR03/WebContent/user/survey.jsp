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

	<span class="error">${error}</span>

	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Sujet</td>
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
	<br/>

</body>
</html>