package br.com.ifsp.es4a4.projeto.utils.mail;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SenderEmail {
	
	private Properties props;
	private final SmtpAuthenticator auth;
	
	@Autowired
	public SenderEmail(@Qualifier("mailer") SmtpAuthenticator smtp) {
		this.auth = smtp;
	}
	
	public boolean sendEmail(EmailDto email) {
		
		try {

			MimeMessage message = this.configMessage(email, this.config());
			Transport.send(message);
		
			log.info("Email enviado com sucesso!");
			return true;
		}
		catch(Exception e) {
			log.error("Erro ao enviar email: " + e.getMessage());
			return false;
		}
	}

	private Session config() {

		props = new Properties();
		  
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		return Session.getInstance(props, auth);
	}
	
	private MimeMessage configMessage(EmailDto email, Session session) throws AddressException, MessagingException {
		MimeMessage message = new MimeMessage(session);
				
		for(String recipient : email.getRecipientsTO())
			message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
		
		if(Objects.nonNull(email.getRecipientsCC())) {
			for(String recipient : email.getRecipientsCC())
				message.addRecipient(RecipientType.CC, new InternetAddress(recipient));
		}
		
		if(Objects.nonNull(email.getRecipientsBCC())) {
			for(String recipient : email.getRecipientsBCC())
				message.addRecipient(RecipientType.BCC, new InternetAddress(recipient));
		}
				
		message.setSentDate(email.getDateSent());
		message.setSubject(email.getTitle());
		
		Multipart multipart = new MimeMultipart();
		
		MimeBodyPart attachmentText = new MimeBodyPart();
		attachmentText.setContent(email.getMsgHTML(),"text/html; charset=UTF-8");
		multipart.addBodyPart(attachmentText);
		
		if(Objects.nonNull(email.getFiles())) {
			for(File file : email.getFiles())
				this.setAttachment(multipart, file);
		}
		
		message.setContent(multipart);
		
		return message;
	}
	
	private void setAttachment(Multipart multipart, File file) throws MessagingException {
		
		try {
			MimeBodyPart part = new MimeBodyPart();
			part.setDataHandler(new DataHandler(new FileDataSource(file)));
			part.setFileName(file.getName());
			multipart.addBodyPart(part);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
