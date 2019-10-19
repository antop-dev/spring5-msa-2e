package org.rvslab.chapter03;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailer {
    private final JavaMailSender mailSender;

    public Mailer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Registration");
        mailMessage.setText("Successfully Registered");
        mailSender.send(mailMessage);
    }

}
