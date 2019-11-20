package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private JavaMailSender javaMailSender;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    public User save(User user){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("andrinho.num7@gmail.com");
        msg.setFrom("andrinho.num7@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        System.out.println(msg.getFrom());
        System.out.println(msg.getTo());
        System.out.println(msg.getSubject());
        System.out.println(msg.getText());

        javaMailSender.send(msg);

        return userRepository.save(user);

    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
