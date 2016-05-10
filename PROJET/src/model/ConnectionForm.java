package model;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import model.dao.UserDao;

public final class ConnectionForm {

	private static final String FIELD_MAIL 	= "mail";
	private static final String FIELD_PWD 	= "password";
	
	private String resultat;
	private Map<String, String> error = new HashMap<String, String>();
	
	public ConnectionForm() {}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErrorMap() {
		return error;
	}
	
	
	public UserDao connect(HttpServletRequest request) 
	{
		UserDao user 	= null;
		String mail 	= request.getParameter(FIELD_MAIL).trim();
		String password = request.getParameter(FIELD_PWD).trim();
		
		if(mail.length() == 0) setError(FIELD_MAIL, "Veuillez remplir le champs Login");
		if(password.length() == 0) setError(FIELD_PWD, "Veuillez remplir le champs mot de passe");
		
		// No errors
		if (error.isEmpty()) 
		{
			if ( (user = UserDao.find(mail, password)) == null)
			{
				setError(FIELD_PWD, "Email or password is invalid");
			}
		}
		return user;
	}
	
	public void setError(String champ, String message) {
		error.put(champ, message);
	}
}
