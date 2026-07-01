package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bibili.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {

    public Utilisateur findByLogin(String login);

}
