package iset.bizerte.elearning.Service.IMPL;




import java.io.IOException;
import java.util.HashMap;



import iset.bizerte.elearning.Config.JwtService;
import iset.bizerte.elearning.Dto.*;
import iset.bizerte.elearning.Entity.*;
import iset.bizerte.elearning.Lisner.RegistrationCompleteEvent;
import iset.bizerte.elearning.Repository.AdminRepository;
import iset.bizerte.elearning.Repository.EtudiantRepository;
import iset.bizerte.elearning.Repository.TokenRepository;
import iset.bizerte.elearning.Repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import static iset.bizerte.elearning.Service.UserService.applicationUrl;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final EtudiantRepository etudiantRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher publisher;
    private final AdminRepository adminRepository;

    //methode register
    public ResponseEntity<Response>  register(RegisterRequeste userRequest, final HttpServletRequest request) {
        // System.err.println(userRequest.getRole());
        boolean userExists = repository.findAll()
                .stream()
                .anyMatch(user -> userRequest.getEmail().equalsIgnoreCase(user.getEmail()));
        if (userExists) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseMessage("User with provided email  already exists!")
                    .build());
        }
        if (userRequest instanceof EtudiantDto) {
            Etudiant user = new Etudiant();
            user = EtudiantDto.toEntity((EtudiantDto) userRequest);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setRole(Erole.ETUDIANT);
            var savedUser = repository.save(user);
            publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));
            return new ResponseEntity<>(
                    Response.builder()
                            .responseMessage("Success! Please, check your email to complete your registration")
                            .email(savedUser.getEmail())
                            .build(),
                    HttpStatus.CREATED
            );
        }else if (userRequest instanceof AdministrateurDto) {
            Administrateur user = new Administrateur();
            user = AdministrateurDto.toEntity((AdministrateurDto) userRequest);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setRole(Erole.ADMIN);
            var savedUser = repository.save(user);
            publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));
            return new ResponseEntity<>(
                    Response.builder()
                            .responseMessage("Success! Please, check your email to complete your registration")
                            .email(savedUser.getEmail())
                            .build(),
                    HttpStatus.CREATED
            );
        } else if (userRequest instanceof EnseignantDto) {
            Enseignant user = new Enseignant();
            user = EnseignantDto.toEntity((EnseignantDto) userRequest);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setEnabled(true);
            user.setRole(Erole.ENSEIGANT);
            var savedUser = repository.save(user);
            //publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));
            return new ResponseEntity<>(
                    Response.builder()
                            .responseMessage("Success! Please, check your email to complete your registration")
                            .email(savedUser.getEmail())
                            .build(),
                    HttpStatus.CREATED
            );
        } else if (userRequest instanceof ParentDto) {
            Parent user = new Parent();
            user = ParentDto.toEntity((ParentDto) userRequest);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setRole(Erole.PARENT);
            var savedUser = repository.save(user);
            publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));
            return new ResponseEntity<>(
                    Response.builder()
                            .responseMessage("Success! Please, check your email to complete your registration")
                            .email(savedUser.getEmail())
                            .build(),
                    HttpStatus.CREATED
            );
        } else {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseMessage("Invalid role or user type!")
                    .build());
        }
    }





    @PostConstruct
    public void createDefaultAdmin() {
        Administrateur userAdmin =new Administrateur();


        String email50 = "admin@mail.com";
        if (!repository.existsByEmail(email50)) {
            userAdmin.setEmail("admin@mail.com");
            userAdmin.setFirstName(" naili");
            userAdmin.setLastName(" salah");
            userAdmin.setPassword(passwordEncoder.encode("adm"));
            userAdmin.setRole(Erole.ETUDIANT);
            userAdmin.setEnabled(true);
            adminRepository.save(userAdmin);
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var claims = new HashMap<String, Object>();
        claims.put("fullname", user.getFirstName()+user.getLastName());
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());
        var jwtToken = jwtService.generateToken(claims,user);

        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }


    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}




