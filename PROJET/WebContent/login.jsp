<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
</head>
<body>

	<form id="form_login" method="post" action="<%=application.getContextPath()%>/connexion">
	    <fieldset>
	    	<legend>Connexion</legend>
	
	        <label for="mail">Login <span class="required">*</span></label>
	        <input type="text" id="mail" name="mail" size="20" maxlength="60" />
	        <span class="error">${errors['mail']}</span>
	        <br />
	
	        <label for="password">Mot de passe <span class="required">*</span></label>
	        <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
	        <span class="error">${errors['password']}</span>
	        <br />
	
	        <input type="submit" value="Connexion" />
	        <br />
	        
	        <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
	    </fieldset>
	</form>
	
	<span class="erreur">${error}</span>
	
</body>
</html>