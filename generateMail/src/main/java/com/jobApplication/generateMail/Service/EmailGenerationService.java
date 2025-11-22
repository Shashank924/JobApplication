package com.jobApplication.generateMail.Service;

import com.jobApplication.generateMail.Model.MailData;
import com.jobApplication.generateMail.Model.Recruiter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailGenerationService {

    @Autowired
    private RecruiterPdfExtractorService recruiterPdfExtractorService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public void generateEmail() {

        List<Recruiter> recruiters = recruiterPdfExtractorService.extractRecruiterFromPdf();

        for(Recruiter recruiter : recruiters) {

            MailData mailData = new MailData();
            mailData.setReceiverEmail(recruiter.getEmail());
            mailData.setReceiverName(recruiter.getName());
            mailData.setReceiverCompany(recruiter.getCompany());
            mailData.setSubject("Application for Backend Engineer/ Software Engineer | 2+ YOE");

            rabbitTemplate.convertAndSend(exchange , routingKey , mailData);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
