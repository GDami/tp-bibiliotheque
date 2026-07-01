package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bibili.model.Editeur;

public interface IDAOEditeur extends JpaRepository<Editeur, Integer> {

    @Query("select e from Editeur e left join fetch e.livres where e.id = :idEditeur")
    public Editeur findByIdWithLivres(@Param("idEditeur") Integer idEditeur);

}
