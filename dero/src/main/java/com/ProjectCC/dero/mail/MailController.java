package com.ProjectCC.dero.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value="/api/mail")
public class MailController {

    @Autowired
    private SmtpMailSender smtpMailSender;

    @GetMapping(value = "/accept/{email}")
    public void sendMail(@PathVariable String email) throws MessagingException {
        smtpMailSender.send(email, "Registration", "You have been successfully registered");
    }

    @GetMapping(value = "/refuse/{email}/{description}")
    public void sendMail(@PathVariable String email, @PathVariable String description) throws MessagingException {
        smtpMailSender.send(email, "Registration", description);
    }
}
