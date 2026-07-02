package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bibili.model.Avis;

public interface IDAOAvis extends JpaRepository<Avis, Integer> {
    @Query("select a from Avis a left join FETCH a.livre where a.id = :idAvis")
    public Avis findByIdWithLivres(@Param("idAvis") Integer idAvis);

}
