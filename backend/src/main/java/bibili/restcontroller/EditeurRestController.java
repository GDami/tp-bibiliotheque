package bibili.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bibili.dao.IDAOEditeur;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonView;

import bibili.model.Editeur;
import bibili.view.Views;

@RestController
@RequestMapping("/api/editeur")
public class EditeurRestController {

	@Autowired
	IDAOEditeur daoEditeur;

	
	@GetMapping
	@JsonView(Views.Editeur.class)
	public List<Editeur> chercherTous()  
	{
		return daoEditeur.findAll();	
	}
	
	@GetMapping("/{numero}")
	@JsonView(Views.Editeur.class)
	public Editeur chercherParNumero(@PathVariable Integer numero)  
	{
		return daoEditeur.findById(numero).orElse(null);
	}

	
	@DeleteMapping("/{numero}")
	public void supprimer(@PathVariable Integer numero)  
	{
		daoEditeur.deleteById(numero);
	}
	
	@PostMapping
	public Editeur ajouter(@RequestBody Editeur editeur)  
	{
		return daoEditeur.save(editeur);
	}
	
	@PutMapping("/{numero}")
	public Editeur modifier(@PathVariable Integer id,@RequestBody Editeur editeur)  
	{
		editeur.setId(id);
		return daoEditeur.save(editeur);
	}
}
