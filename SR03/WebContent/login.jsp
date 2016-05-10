<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

	<div class="formulaire">
		<form role="form" id="form_login" method="post" action="<%=application.getContextPath()%>/connexion">
		
			<div class="form-group">
				<label for="mail">Login</label>
				<input class="form-control" type="text" id="mail" name="mail" size="20" maxlength="60" />
				<span class="error">${errors['mail']}</span>
			</div>
	
			<div class="form-group">
				<label for="password">Mot de passe</label>
		        <input class="form-control" type="password" id="password" name="password" value="" size="20" maxlength="20" />
		        <span class="error">${errors['password']}</span>
			</div>
	
	        <input type="submit" value="Connexion" class="btn btn-default" />
	        
		</form>
		
		<span class="erreur">${error}</span>
	</div>
	
</body>
</html>