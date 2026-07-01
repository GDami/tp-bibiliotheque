package bibili.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bibili.model.Collection;

public interface IDAOCollection extends JpaRepository<Collection,Integer> {
    
}
