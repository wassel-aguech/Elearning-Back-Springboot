package iset.bizerte.elearning.Service;

import iset.bizerte.elearning.Entity.Response;
import iset.bizerte.elearning.Entity.User;
import iset.bizerte.elearning.Entity.VerificationToken;
import org.springframework.http.ResponseEntity;


public interface VerificationTokenService {

    void saveUserVerificationToken(User user, String token);
    String validateToken(String token);
    ResponseEntity<Response> verifyEmail(String token);
    VerificationToken generateNewVerificationToken(String oldToken);
}


