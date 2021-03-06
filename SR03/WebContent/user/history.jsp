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
	        <th>Detail</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach var="course" items="${courses }">
	      	<tr class="tr-course">
	      		<td>${course.survey.id }</td>
	      		<td>${course.survey.subject.name }</td>
	      		<td>${course.score }</td>
	      		<td>${course.time }</td>
	      		<td><a href="<%=application.getContextPath()%>/user/survey/history/${course.id}">Detail</a></td>	
	      	</tr>
	      </c:forEach>
	    </tbody>
	  </table>
	</div>
	<ul class="pagination">
		<%
			int pageNumber = (Integer) request.getAttribute("pageNum");
			for (int i=1; i<=pageNumber; i++) {
				out.println("<li id=\"" + String.valueOf(i) + "\" class=\"li-page\"><a>" + String.valueOf(i) + "</a></li>");
			}
		%>

		
	</ul>
	<br/>
	<a href="<%=application.getContextPath()%>/user/user.jsp" class="btn btn-success" role="button">Retour</a>

	<script type="text/javascript">
	$(function() {
		  var items = $(".tr-course");
		  var numItems = items.length;
		  var perPage = 10;
		  var numPage = Math.ceil(numItems / perPage);
		  var current = 1;
		  var pageItems = $(".li-page");
		  items.slice(perPage).hide();
		  $("#" + current).addClass("active");
		  for (i=1; i<=numPage; i++) {
			  $("#" + i).on("click", function() {
				  update($(this).attr("id"));
			  });
		  }		  
		  
		  function update(pageNumber)
		  {
			  var showFrom = perPage * (pageNumber-1);
		      var showTo = showFrom + perPage;
		      if (showTo > numItems) {
		    	  showTo = numItems;
		      }
		      items.hide();
		      items.slice(showFrom, showTo).show();
		      $("#" + current).removeClass("active");
		      $("#" + pageNumber).addClass("active");
		      current = pageNumber;
		  }
		});
	</script>
</body>
</html>