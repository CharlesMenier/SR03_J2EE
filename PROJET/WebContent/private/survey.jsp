<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<td>ID</td>
				<td>Sujet</td>
				<td>Actif</td>
				<td>Modifier</td>
				<td>Supprimer</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="surveys" items="${surveys}">
				<tr>
					<td>${surveys.id}</td>
					<td>${surveys.subject.name}</td>
					<td>${surveys.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form id="form_survey" method="POST" action="Login">
	
		<select id="selectSubject" name="selectSubject">
            <c:forEach var="subjects" items="${subjects}">
                <option value="${subjects.id}">${subjects.name}</option>
            </c:forEach>
        </select>
	
	
		<input type="text" id="subject" name="subject" placeholder="Sujet"/>
        <span class="erreur">${errors['subject']}</span>
        
		<input type="checkbox" id="status" name="status"/>
		<span class="erreur">${errors['status']}</span>
		
		<input type="submit" value="Ajouter"/>
	</form>

</body>
</html>