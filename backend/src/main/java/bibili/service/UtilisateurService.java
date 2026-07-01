package bibili.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibili.dao.IDAOUtilisateur;
import bibili.model.Utilisateur;

@Service
public class UtilisateurService {

    @Autowired
    private IDAOUtilisateur daoUtilisateur;

    public Utilisateur getByLogin(String login) {
        return daoUtilisateur.findByLogin(login);
    }

}
