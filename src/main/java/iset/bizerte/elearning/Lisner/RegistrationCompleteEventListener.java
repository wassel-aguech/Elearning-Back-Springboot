package iset.bizerte.elearning.Lisner;
import java.util.UUID;

import iset.bizerte.elearning.Entity.EmailDetails;
import iset.bizerte.elearning.Entity.User;
import iset.bizerte.elearning.Service.EmailService;
import iset.bizerte.elearning.Service.VerificationTokenService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final VerificationTokenService tokenService;
    private final EmailService emailService;
    private User user;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        user = event.getUser();
        // 2. Create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        // 3. Save the verification token for the user
        tokenService.saveUserVerificationToken(user, verificationToken);
        // 4. Build the verification url to be sent to the user
        String url = event.getApplicationUrl() + "/api/v1/verify/email?token=" + verificationToken;
        // 5. Send the email
        sendVerificationEmail(url);
    }
    public void sendVerificationEmail(String url) {
        String subject = "Email Verification";
        String mailContent = "<p> Hi, " + user.getFirstName()  + user.getLastName() +", </p>" +
                "<p>Thank you for registering with us, " +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" +
                "<p> Thank you <br> Users Registration Portal Service";


        EmailDetails emailDetails = getEmailDetails(subject, mailContent);
        emailService.sendMail(emailDetails);
    }
    public void sendPasswordResetVerificationEmail(String url) {
        String subject = "Password Reset Request Verification";
        String mailContent = "<p> Hi, "+ user.getFirstName()  + user.getLastName() +", </p>"+
                "<p><b>You recently requested to reset your password,</b>"+
                "Please, follow the link below to complete the action.</p>"+
                "<a href=\"" +url+ "\">Reset password</a>"+
                "<p> Users Registration Portal Service";
        EmailDetails emailDetails = getEmailDetails(subject, mailContent);
        emailService.sendMail(emailDetails);
    }
    private EmailDetails getEmailDetails(String subject, String mailContent) {
        return EmailDetails.builder()
                .subject(subject)
                .to(user.getEmail())
                .messageBody(mailContent)
                .build();
    }
}


