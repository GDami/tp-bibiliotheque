package bibili.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bibili.model.Utilisateur;
import bibili.service.UtilisateurService;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Autowired
    private UtilisateurService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String token = authHeader.substring(7);
            String login = JwtUtils.validate(token);
            Utilisateur personne = this.service.getByLogin(login);

            if (personne != null) {
                List<GrantedAuthority> authorities = new ArrayList<>();

                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                if (personne.isAdmin()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }

                Authentication auth = new UsernamePasswordAuthenticationToken(login, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // On pense à passer à la suite
        filterChain.doFilter(request, response);
    }
}
