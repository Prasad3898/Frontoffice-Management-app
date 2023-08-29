package com.prasad.utility;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendMail(String to, String subject, String body) {

		boolean isSent = false;
		try {
			/*
			 * JavaMail API to create a new instance of the MimeMessage class.
			 * 
			 * The MimeMessage class represents an email message that can be sent over the
			 * internet using the Simple Mail Transfer Protocol (SMTP). It allows you to
			 * specify the various parts of an email message, such as the sender, recipient,
			 * subject, body, and any attachments.
			 */

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			javaMailSender.send(message);
			isSent = true;
		} catch (Exception e) {
           e.printStackTrace();
		}
		return isSent;
	}

}
