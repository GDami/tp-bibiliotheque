package bibili.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bibili.restcontroller.dto.request.AuthRequest;
import bibili.restcontroller.dto.response.AuthTokenResponse;
import bibili.security.JwtUtils;


@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public AuthTokenResponse auth(@RequestBody AuthRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(request.login(), request.password());

        // On demande à Spring Security de vérifier l'authentification (username & password OK ?)
        this.authenticationManager.authenticate(auth);

        // Si on arrive ici, c'est que l'authentification est OK
        return new AuthTokenResponse(JwtUtils.generate(request.login()));
    }
}
