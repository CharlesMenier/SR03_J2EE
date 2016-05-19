<%@page import="model.dao.QuestionDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.AnswerDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
						<%
							
							ArrayList<QuestionDao> questions = (ArrayList<QuestionDao>) request.getAttribute("questions");
							for (int i=0; i<questions.size(); i++) {
								out.println("<li class=\"list-group-item\">");
								out.println("<label class=\"label label-info\">" +
													questions.get(i).getLabel() + "</label>");
								out.println("<select class=\"form-control page" + String.valueOf(i/3 + 1) +"\" multiple=\"multiple\" id=\"question" + 
													String.valueOf(questions.get(i).getId()) + "\" name=\"" + questions.get(i).getLabel() + "\">");
								ArrayList<AnswerDao> answers = (ArrayList<AnswerDao>) request.getAttribute("question" + String.valueOf(questions.get(i).getId()));		
								for (AnswerDao answer : answers) {
									System.out.println(answer.getLabel());
									out.println("<option value=\"" + String.valueOf(answer.getId()) + "\">" +
												answer.getLabel() + "</option>");
								}
								out.println("</select>\n</li>");
							}
						%>
						
						
					</ol>
					<button class="btn btn-success" type="submit">Finaliser</button>
				</form>
			</div>

		</div>

</body>
</html>