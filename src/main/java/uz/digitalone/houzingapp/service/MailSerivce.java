package uz.digitalone.houzingapp.service;

import uz.digitalone.houzingapp.dto.NotificationEmail;

public interface MailSerivce {
    void send(NotificationEmail notificationEmail);
}
