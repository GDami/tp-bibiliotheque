package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bibili.model.Editeur;

public interface IDAOEditeur extends JpaRepository<Editeur, Integer> {

}
