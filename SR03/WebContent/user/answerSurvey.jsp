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
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/jquery.min.js"></script>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
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
							<label class="label label-info">${questions.label }</label><br/>
							<!-- <select class="form-control" id="question${questions.id }" name="${questions.label }"> -->
							<c:forEach var="answers" items="${questions.answers }">
								<!-- <option value="${answers.id }">${answers.label }</option> -->
								<input type="radio" name="${questions.label }" value="${answers.id}"/>${answers.label }<br/>
							</c:forEach>
						</li>
					</c:forEach>						
					
				</ol>
				
				<a id="prev" class="btn btn-default">Précédent</a>
				<a id="next" class="btn btn-default">Suivant</a>
				
				<button class="btn btn-success" type="submit">Terminer</button>
			</form>
			
		</div>

	</div>
		
	<script type="text/javascript">
	$(function() {
		  var items = $(".list-group-item");
		  var numItems = items.length;
		  var perPage = 1;
		  var current = 0;
		  var numPage = (numItems / perPage) - 1;
		  
		  items.slice(perPage).hide();
		  
		  $('#prev').on('click', function(){
			  	if(current > 0)
				{
			  		current--;
			  		update();
				}
		  });
		  
		  $('#next').on('click', function(){
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