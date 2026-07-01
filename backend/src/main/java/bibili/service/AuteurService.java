package bibili.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bibili.idao.IDAOAuteur;
import bibili.model.Auteur;

@Service
public class AuteurService {

	@Autowired
	IDAOAuteur idaoAuteur;

	public List<Auteur> getAll()
	{
		return idaoAuteur.findAll();
	}

	public Auteur getById(Integer id)
	{
		return idaoAuteur.findById(id).orElse(null);
	}

	public Auteur insert(Auteur auteur)
	{
		return idaoAuteur.save(auteur);
	}

	public Auteur update(Auteur auteur)
	{
		return idaoAuteur.save(auteur);
	}

	public void delete(Integer id)
	{
		idaoAuteur.deleteById(id);
	}
}
