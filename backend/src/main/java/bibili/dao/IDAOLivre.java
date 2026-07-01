package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bibili.model.Livre;

public interface IDAOLivre extends JpaRepository<Livre, Integer> {

}
