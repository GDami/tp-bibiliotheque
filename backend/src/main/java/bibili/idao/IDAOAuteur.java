package bibili.idao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bibili.model.Auteur;

public interface IDAOAuteur extends JpaRepository<Auteur,Integer> {

}
