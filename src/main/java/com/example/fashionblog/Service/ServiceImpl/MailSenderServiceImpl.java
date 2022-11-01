package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.CustomException.MailNotSentException;
import com.example.fashionblog.Service.MailSenderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailSenderServiceImpl implements MailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendMail(String recipient, String subject, String body){
       try{
           MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
           helper.setText(body, true);
           helper.setFrom("no-reply@baeblog.com");
           helper.setTo(recipient);
           helper.setSubject(subject);
           mailSender.send(mimeMessage);
       }catch(Exception e){
            throw new MailNotSentException("Email could not be sent");
       }
    }
}
