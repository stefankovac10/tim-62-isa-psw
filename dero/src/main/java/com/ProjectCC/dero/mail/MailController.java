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

    @GetMapping(value = "/accept/{email}/{id}")
    public void accept(@PathVariable String email, @PathVariable String id) throws MessagingException {
        smtpMailSender.send(email, "Registration", "You have been successfully registered." +
                                                            "Please activate your account, clicking on the link below. " +
                                                             "<a href='http://localhost:8080/api/patient/add/"+id+"'>Activate</a>");
    }

    @GetMapping(value = "/refuse/{email}/{description}")
    public void decline(@PathVariable String email, @PathVariable String description) throws MessagingException {
        smtpMailSender.send(email, "Registration", description);
    }
}
