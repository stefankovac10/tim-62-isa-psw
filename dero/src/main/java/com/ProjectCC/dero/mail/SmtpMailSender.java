package com.ProjectCC.dero.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@EnableAsync
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void send (String to, String subject, String body) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message,"utf-8");
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body,true);

        javaMailSender.send(message);
    }
}
