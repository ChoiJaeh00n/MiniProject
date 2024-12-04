package springBootTripShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {		// 이메일 모듈화
	@Autowired
	JavaMailSender mailSender;
	
	public void mailsend(String html, String subject, String fromEmail, String toEmail) {
		
		/* 메일링하는 기본 문법 */
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			msg.setHeader("content-Type", "text/html; charset=UTF-8");
			msg.setContent(html, "text/html; charset=UTF-8");
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
			mailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	}
}
