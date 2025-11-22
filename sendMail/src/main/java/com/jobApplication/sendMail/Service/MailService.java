package com.jobApplication.sendMail.Service;

import com.jobApplication.sendMail.Model.MailData;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    File resume = new File("C:/Users/shash/Downloads/SHASHANK_SHEKHAR_RESUME.pdf");
    String bodyTemplate = """
            Dear %s,
            
            I hope you are doing well. My name is Shashank, and I am a Backend Engineer at TCS with over 2 years of experience building scalable backend services using Java, Spring Boot, and MySQL.
            I am currently exploring new opportunities and wanted to check if there are any software engineering openings that align with my skill set.
            
            I am also passionate about problem–solving — I have solved 1,700+ DSA problems across LeetCode and Codeforces, and I hold the ratings of Specialist on Codeforces and Knight (1900+) on LeetCode.
            
            I have attached my resume below. You can also find my LinkedIn, GitHub and LeetCode profile for reference.
            
            I would greatly appreciate it if you could review my profile and let me know if there are any suitable backend engineer or software developer roles.
            Thank you for your time and consideration.
            
            Regards,
            Shashank Shekhar
            LinkedIn - https://www.linkedin.com/in/shashank-shekhar-94a1a422a/
            LeetCode - https://leetcode.com/u/shashankshekhar955/
            GitHub - https://github.com/Shashank924
            Phone : 6299727370
            """;


    public void sendMail(MailData mailData) throws MessagingException {
        String finalBody = bodyTemplate.formatted(mailData.getReceiverName());
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage , true);

        helper.setFrom("shashankshekhar955@gmail.com");
        helper.setTo(mailData.getReceiverEmail());
        helper.setSubject(mailData.getSubject());
        helper.setText(finalBody , false);
        helper.addAttachment("Resume" , resume);

        mailSender.send(mailMessage);
    }
}
