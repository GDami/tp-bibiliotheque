package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bibili.model.Auteur;

public interface IDAOAuteur extends JpaRepository<Auteur,Integer> {

    @Query("select a from Auteur a left join fetch a.livres where a.id = :idAuteur")
    public Auteur findByIdWithLivres(@Param("idAuteur") Integer idAuteur);

}
