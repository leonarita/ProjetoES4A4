package br.com.ifsp.es4a4.projeto.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
	
	private String username;
	private String password;
	
	public SmtpAuthenticator(String username, String password) {
	    super();
	    this.username = username;
	    this.password = password;
	}
	
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
	    
		if ((username != null) && (username.length() > 0) && (password != null)  && (password.length () > 0)) {
	        return new PasswordAuthentication(username, password);
	    }
	
	    return null;
	}
}