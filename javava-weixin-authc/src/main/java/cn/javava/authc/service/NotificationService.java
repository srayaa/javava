package cn.javava.authc.service;

import org.springframework.scheduling.annotation.Async;

public interface NotificationService {

    @Async
    void sendMail(String subject, String content);

}
