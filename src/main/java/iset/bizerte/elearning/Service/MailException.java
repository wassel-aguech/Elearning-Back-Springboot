package iset.bizerte.elearning.Service;

public class MailException extends org.springframework.mail.MailException {
    public MailException(String message) {
        super(message);
    }
}
