package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.NotificationEmail;
import uz.digitalone.houzingapp.service.MailSerivce;

import static uz.digitalone.houzingapp.config.SecurityConfiguration.senderEmail;


@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailSerivce {
    private final JavaMailSender javaMailSender;

    @Async
    public void send(NotificationEmail notificationEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setSubject(notificationEmail.getSubject());
        message.setTo(notificationEmail.getReceipt());
        message.setText(notificationEmail.getBody());

        try {
            log.info("Activation successfully");
            javaMailSender.send(message);
        }catch (MailException e) {
            log.error("Email not found" + e);
        }
    }

}
