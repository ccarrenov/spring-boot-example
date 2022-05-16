package site.carrenov.app.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

	private static final Logger LOGGER = Logger.getLogger(SendMailService.class.getName());
	
	private static final String MAIL_SMTP_STARTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_SSL_PROTOCOLS= "mail.smtp.ssl.protocols";
	private static final String MAIL_USER= "mail.smtp.user";
	private static final String MAIL_PASS= "mail.smtp.password";

	@Value( "${" + MAIL_SMTP_STARTLS_ENABLE + "}")
	private String smtpStartTLSEnable;
	
	@Value( "${" + MAIL_SMTP_PORT + "}")
	private String smtpPort;
	
	@Value( "${" + MAIL_SMTP_HOST + "}")
	private String smtpHost;
	
	@Value( "${" + MAIL_SMTP_AUTH + "}")
	private String smtpAuth;
	
	@Value( "${" + MAIL_SMTP_SSL_PROTOCOLS + "}")
	private String smtpSslProtocols;
	
	@Value( "${" + MAIL_USER + "}")
	private String mailUser;
	
	@Value( "${" + MAIL_PASS + "}")
	private String mailPass;		
	
	public void buildAndSend(String toList, String subject, String bodyMessage) {
		LOGGER.info("BUILD MAIL SUCCESSFULL");

		final String user = mailUser;// outlook user
		final String password = mailPass;// password user

		// Get the session object
		Properties props = new Properties();
		  props.put(MAIL_SMTP_STARTLS_ENABLE, smtpStartTLSEnable);
		  props.put(MAIL_SMTP_PORT, smtpPort);
		  props.put(MAIL_SMTP_HOST, smtpHost);
		  props.put(MAIL_SMTP_AUTH, smtpAuth); 
		  props.put(MAIL_SMTP_SSL_PROTOCOLS, smtpSslProtocols);

		  Session session = Session.getInstance(props, new Authenticator() {          
		      @Override
		      protected PasswordAuthentication getPasswordAuthentication() {
		          return new PasswordAuthentication(user,
		                  password);          
		      }       
		  });

		// Compose the message
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			String[] toArray = toList.split(",");
			InternetAddress[] emails = new InternetAddress[toArray.length];
			int i = 0;
			for (String to : toArray) {
				emails[i] = new InternetAddress(to);
				i++;
			}
			message.addRecipients(Message.RecipientType.TO, emails);
			message.setSubject(subject);
			message.setContent(bodyMessage, "text/html; charset=utf-8");			

			// send the message
			Transport.send(message);
			LOGGER.info("SEND MAIL SUCCESSFULL");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public String readMailTemplate(String path) {
		String html = "";

		try (InputStream resource = new ClassPathResource(path).getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
			html = reader.lines().collect(Collectors.joining("\n"));
		} catch (Exception ex) {
			LOGGER.error("Error", ex);
		}
		return html;
	}
	
}
