package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Entity.EmailDetails;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    public String sendSimpleMail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getTo());
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setText(emailDetails.getMessageBody());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (MailException e) {
            throw new MailException("erreur") {
            };
        }
    }

    public void sendMail(EmailDetails emailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetails.getTo());
            mimeMessageHelper.setText(emailDetails.getMessageBody(), true);
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            javaMailSender.send(mimeMessage);
        }
        catch (Exception e) {
            throw new MailException(e.getMessage());
        }




    }
















}




