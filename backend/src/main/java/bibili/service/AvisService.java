package bibili.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibili.dao.IDAOAvis;
import bibili.model.Avis;

@Service
public class AvisService {

    @Autowired
    IDAOAvis daoAvis;

    public List<Avis> getAllAvis() {
        return daoAvis.findAll();
    }
    public Avis getAvisById(Integer id) {
        return daoAvis.findById(id).orElse(null);
    }
    public Avis insertAvis(Avis avis) {
        return daoAvis.save(avis);
    }
    public Avis updateAvis(Avis avis) {
        return daoAvis.save(avis);
    }
    public void deleteAvis(Integer id) {
        daoAvis.deleteById(id);
    }
    public Avis getAvisByIdWithLivres(Integer id) {
        return daoAvis.findByIdWithLivres(id);
    }
}
