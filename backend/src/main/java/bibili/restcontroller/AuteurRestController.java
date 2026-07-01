package bibili.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import bibili.model.Auteur;
import bibili.service.AuteurService;
import bibili.view.Views;

@RestController
@RequestMapping("/api/auteur")
public class AuteurRestController {

	@Autowired
	AuteurService auteurSrv;

	@GetMapping
	@JsonView(Views.Auteur.class)
	public List<Auteur> chercherTous() {
		return auteurSrv.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Auteur.class)
	public Auteur chercherParId(@PathVariable Integer id) {
		return auteurSrv.getById(id);
	}

	@GetMapping("/{id}/livres")
	@JsonView(Views.AuteurWithLivres.class)
	public Auteur chercherParIdAvecLivres(@PathVariable Integer id) {
		return auteurSrv.getByIdWithLivres(id);
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id) {
		auteurSrv.delete(id);
	}

	@PostMapping
	public Auteur ajouter(@RequestBody Auteur auteur) {
		auteurSrv.insert(auteur);
		return auteur;
	}

	@PutMapping("/{id}")
	public Auteur modifier(@PathVariable Integer id, @RequestBody Auteur auteur) {
		auteur.setId(id);
		auteurSrv.update(auteur);
		return auteur;
	}
}
