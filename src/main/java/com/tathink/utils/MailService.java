package com.tathink.utils;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:connection.properties")
public class MailService {
	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${saro.mail.smtp.mail}")
	String form;
	
	public boolean sendMail(String to, String subject, String content)
	{
		MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare(MimeMessage mimeMessage) throws Exception
			{
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				mimeMessage.setFrom(new InternetAddress(form));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(content, "utf-8", "html");
			}
		};
 
		try
		{
			javaMailSender.send(preparator);
			return true;
		}
		catch (MailException me)
		{
			me.printStackTrace();
			return false;
		}
	}
}
