package com.bridgelabz.bookstoreapplication.utility;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j
public class EmailSenderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String emailId, String fN, String lN, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(emailId);
            helper.setSubject(subject);
            helper.setText("<h2><b>Dear " + fN + " " + lN + ",</b></h2>" +
                    body +
                    "<h3><b>With Kind Regards,<br>Team Book Store</b></h3>", true);
            javaMailSender.send(mimeMessage);
            log.info("Mail Sent Successfully to : " + emailId);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}