package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bibili.model.Collection;

public interface IDAOCollection extends JpaRepository<Collection,Integer> {

    @Query("select c from Collection c left join fetch c.livres where c.id = :idCollection")
    public Collection findByIdWithLivres(@Param("idCollection") Integer idCollection);
    
}
