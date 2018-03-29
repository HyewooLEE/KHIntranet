package action.work;
  
import javax.mail.Authenticator;
import javax.mail.*;

public class SMTPAuthenticator extends Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("destiny8548", "fkaus445!@#");
	}
}
