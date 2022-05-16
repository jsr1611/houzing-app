package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.digitalone.houzingapp.dto.NotificationEmail;
import uz.digitalone.houzingapp.service.MailSerivce;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

import static uz.digitalone.houzingapp.config.SecurityConfiguration.senderEmail;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MailServiceImpl implements MailSerivce {
    private final JavaMailSender javaMailSender;

    @Async
    public void send(NotificationEmail notificationEmail){
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            msg.setSubject(notificationEmail.getSubject());
            msg.setFrom(senderEmail);
            msg.setText(notificationEmail.getBody(), "UTF-8", "html");
            msg.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(notificationEmail.getReceiver(),
                            "fork",
                            "UTF-8"));


//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(senderEmail);
//        message.setSubject(notificationEmail.getSubject());
//        message.setTo(notificationEmail.getReceiver());
//        message.setText(notificationEmail.getBody());

//        try {
            javaMailSender.send(msg);
            log.info("Activation successfully");
//            javaMailSender.send(message);

        }catch (MessagingException | UnsupportedEncodingException e){
            log.error(e.getMessage());
        }catch (MailException e) {
            log.error("Email not found" + e);
        }
    }

}
