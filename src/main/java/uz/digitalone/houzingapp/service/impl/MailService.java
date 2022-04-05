package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.NotificationEmail;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Async
    void send(NotificationEmail notificationEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("komiloff.d2505@gmail.com");
        message.setTo(notificationEmail.getReceiver());
        message.setSubject(notificationEmail.getSubject());
        message.setText(notificationEmail.getBody());
        mailSender.send(message);
    }
}
