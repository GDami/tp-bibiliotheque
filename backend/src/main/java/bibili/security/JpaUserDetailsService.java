package bibili.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bibili.model.Utilisateur;
import bibili.service.UtilisateurService;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur personne = this.service.getByLogin(username);

        if (personne == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return User.builder()
            .username(username)
            .password(personne.getPassword())
            .build()
        ;
    }
}
