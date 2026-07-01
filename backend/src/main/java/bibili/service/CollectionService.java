package bibili.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibili.dao.IDAOCollection;
import bibili.model.Collection;

@Service
public class CollectionService {

    @Autowired
    IDAOCollection daoCollection;

    public List<Collection> getAll() {
        return daoCollection.findAll();
    }

    public Collection getById(Integer id) {
        return daoCollection.findById(id).orElse(null);
    }

	public Collection getByIdWithLivres(Integer id)	{
		return daoCollection.findByIdWithLivres(id);
	}

    public Collection insert(Collection collection) {
        return daoCollection.save(collection);
    }

    public Collection update(Collection collection) {
        return daoCollection.save(collection);
    }

    public void delete(Integer id) {
        daoCollection.deleteById(id);
    }
}
