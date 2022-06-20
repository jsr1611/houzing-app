package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.digitalone.houzingapp.dto.NotificationEmail;
import uz.digitalone.houzingapp.service.MailSerivce;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MailServiceImpl implements MailSerivce {
    final String senderEmail = "uzbdevjs@gmail.com";
    final String somethingElse = "lbvfdcglnzluctbp";

    @Async
    public void send(NotificationEmail notificationEmail){


        Properties set = new Properties();
        //Set values to the property
        set.put("mail.smtp.starttls.enable", "true");
        set.put("mail.smtp.auth", "true");
        set.put("mail.smtp.host", "smtp.gmail.com");
        set.put("mail.smtp.port", "587");

        Session session = Session.getInstance(set,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, somethingElse);
            }});
        session.setDebug(true);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject(notificationEmail.getSubject());
            msg.setFrom(new InternetAddress(senderEmail));
            msg.setText(notificationEmail.getBody(), "UTF-8", "html");
            msg.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(notificationEmail.getReceiver(),
                            notificationEmail.getReceiver(),
                            "UTF-8"));

            Transport.send(msg);
            log.info("Activation email sent successfully");

        }catch (MessagingException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

}
