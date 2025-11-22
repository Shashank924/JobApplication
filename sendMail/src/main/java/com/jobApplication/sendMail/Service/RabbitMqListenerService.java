package com.jobApplication.sendMail.Service;

import com.jobApplication.sendMail.Model.MailData;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqListenerService {
    @Autowired
    private MailService mailService;

    @RabbitListener(queues = "mail.queue")
    public void MailListener(MailData mailData) throws MessagingException {
        mailService.sendMail(mailData);
    }
}
