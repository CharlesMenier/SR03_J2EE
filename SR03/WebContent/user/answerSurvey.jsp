<%@page import="model.dao.QuestionDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.AnswerDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
<title>Questionnaire ${survey.id}</title>
</head>
<body>
<div class="container">

			<div >
				<div>
					<h1>Questionnaire ${survey.id }</h1>
					<h1>Stagiaire : ${sessionUser.name }</h1>
				</div>
				<form id="surveyForm" method="post" action="<%=application.getContextPath()%>/user/survey/submit/${survey.id}">
					<ol class="list-group">
					
						<c:forEach var="questions" items="${questions }">
							<li class="list-group-item">
								<label class="label label-info">${questions.label }</label>
								<select class="form-control" multiple="multiple" id="question${questions.id }" name="${questions.label }">
								<c:forEach var="answers" items="${questions.answers }">
									<option value="${answers.id }">${answers.label }</option>
								</c:forEach>
								</select>
						</c:forEach>						
						
					</ol>
					<button class="btn btn-success" type="submit">Terminer</button>
				</form>
			</div>

		</div>

</body>
</html>