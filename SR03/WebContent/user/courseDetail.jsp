<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/jquery.min.js"></script>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div>
	<h2><span class="label label-default">Parcours ${courseId}</span></h2>
	<h2><span class="label label-default">Nom : ${sessionUser.name}</span></h2>
</div>

<br/>
<ol class="list-group">
	
	<c:forEach var="question" items="${courseHistory}">
		<li class="list-group-item tr-question">
			<label class="label label-info">${question.key.label}</label><br/>
			<!-- <select class="form-control" id="question${questions.id }" name="${questions.label }"> -->
			<c:forEach var="answer" items="${question.value}">
				<!-- <option value="${answers.id }">${answers.label }</option> -->
				<c:choose>
					<c:when test="${answer.selected eq true}">
						<input type="radio" checked="checked"/>${answer.label }<br/>
					</c:when>
					<c:otherwise>
						<input type="radio" />${answer.label }<br/>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${question.key.correct eq true}">
					<label class="label label-success">Correcte</label><br/>
				</c:when>
				<c:otherwise>
					<label class="label label-danger">Incorrect</label><br/>
				</c:otherwise>
			</c:choose>
			</label><br/>
		</li>
	</c:forEach>						
	
</ol>
<ul class="pagination">
		<%
			int pageNumber = (Integer) request.getAttribute("pageNum");
			for (int i=1; i<=pageNumber; i++) {
				out.println("<li id=\"" + String.valueOf(i) + "\" class=\"li-page\"><a>" + String.valueOf(i) + "</a></li>");
			}
		%>
</ul>

<a href="<%=application.getContextPath()%>/user/survey/history" class="btn btn-success" role="button">Retour</a>

<script type="text/javascript">
	$(function() {
		  var items = $(".tr-question");
		  var numItems = items.length;
		  var perPage = 5;
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