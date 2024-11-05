package iset.bizerte.elearning.Service.IMPL;


import java.util.Calendar;
import java.util.UUID;
import iset.bizerte.elearning.Entity.Response;
import iset.bizerte.elearning.Entity.User;
import iset.bizerte.elearning.Entity.VerificationToken;
import iset.bizerte.elearning.Repository.UserRepository;
import iset.bizerte.elearning.Repository.VerificationTokenRepository;
import iset.bizerte.elearning.Service.VerificationTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import static iset.bizerte.elearning.Service.UserService.applicationUrl;


@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final HttpServletRequest servletRequest;
    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token,user);
        tokenRepository.save(verificationToken);
    }
    @Override
    public String validateToken(String token) {

        //Existance Token
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "Invalid verification token";
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0)  {
            return "Token already expired";
        }
        //user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
    @Override
    public ResponseEntity<Response> verifyEmail(String token) {


        String url = applicationUrl(servletRequest)+"/api/v1/verify/resend-verification-token?token="+token;
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken.getUser().isEnabled()){
            return ResponseEntity.ok().body(
                    Response.builder()
                            .responseMessage("This account has already been verified, please login")
                            .email(verificationToken.getUser().getEmail())
                            .build()
            );
        }
        String verificationResult = this.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body(
                    Response.builder()
                            .responseMessage("Email verified successfully. Now you can login to your account" )
                            .email(verificationToken.getUser().getEmail())
                            .build()
            );
        }
        return ResponseEntity.ok().body(
                Response.builder()
                        .responseMessage("Invalid verification link, <a href=\"" + url + "\"> Get a new verification link. </a>")
                        .email(verificationToken.getUser().getEmail())
                        .build()
        );
    }
    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var verificationTokenTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(verificationTokenTime.getTokenExpirationTime());
        return tokenRepository.save(verificationToken);
    }
}




