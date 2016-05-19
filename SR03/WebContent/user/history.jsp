<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
<title>History</title>
</head>
<body>
<div>
	<h1><span class="label label-info">${sessionUser.name }</span></h1>
</div>
<div class="container">
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Questionnaire ID</th>
        <th>Questionnaire sujet</th>
        <th>Note</th>
        <th>Duree</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="course" items="${courses }">
      	<tr>
      		<td>${course.survey.id }</td>
      		<td>${course.survey.subject.name }</td>
      		<td>${course.score }</td>
      		<td>${course.time }</td>		
      	</tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<a href="<%=application.getContextPath()%>/user/user.jsp" class="btn btn-success" role="button">Retourner</a>
</body>
</html>