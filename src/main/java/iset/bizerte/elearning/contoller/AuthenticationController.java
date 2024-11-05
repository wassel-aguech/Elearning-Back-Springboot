package iset.bizerte.elearning.contoller;


import iset.bizerte.elearning.Dto.*;
import iset.bizerte.elearning.Entity.Response;

import iset.bizerte.elearning.Service.IMPL.AuthenticationService;
import iset.bizerte.elearning.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/registeretudiant")
    public ResponseEntity<Response> register(
            @RequestBody @Valid EtudiantDto userRequest,
            HttpServletRequest request
    )  {
        return service.register(userRequest,request);
    }




    @PostMapping("/registerEnseignant")
    public ResponseEntity<Response> register(
            @RequestBody @Valid EnseignantDto userRequest,
            HttpServletRequest request
    )  {
        return service.register(userRequest,request);
    }









    @PostMapping("/registerAdmin")
    public ResponseEntity<Response> registerAdmin(
            @RequestBody @Valid AdministrateurDto userRequest,
            HttpServletRequest request
    )  {
        return service.register(userRequest,request);
    }







    @PostMapping("/registerParent")
    public ResponseEntity<Response> register(
            @RequestBody @Valid ParentDto userRequest,
            HttpServletRequest request
    )  {
        return service.register(userRequest,request);
    }











    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}


