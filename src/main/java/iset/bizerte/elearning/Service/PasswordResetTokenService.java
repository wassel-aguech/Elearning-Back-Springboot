package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Entity.ChangePasswordResetRequest;
import org.springframework.http.ResponseEntity;

public interface PasswordResetTokenService {
    ResponseEntity<String> verifyEmail(String email);
    ResponseEntity<String> verifyOtp(Integer otp, String email);

    ResponseEntity<String> changePasswordHandler(
            ChangePasswordResetRequest changePasswordResetRequest,
            String email
    );




}




