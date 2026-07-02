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

import bibili.model.Avis;
import bibili.service.AvisService;
import bibili.view.Views;




@RestController
@RequestMapping("/api/avis")
public class AvisController {
    @Autowired
    AvisService avisSrv;
    
    @GetMapping
    @JsonView(Views.Avis.class)
    public List<Avis> chercherTous() {
        return avisSrv.getAllAvis();
    }
    @GetMapping("/{id}")
    @JsonView(Views.Avis.class)
    public Avis chercherParId(@PathVariable Integer id) {
        return avisSrv.getAvisById(id);
    }

    @GetMapping("/{id}/livres")
    @JsonView(Views.AvisWithLivres.class)
    public Avis chercherParIdAvecLivres(@PathVariable Integer id) {
        return avisSrv.getAvisByIdWithLivres(id);
    }

    @PostMapping
    public Avis ajoutAvis(@RequestBody Avis avis) {
        return avisSrv.insertAvis(avis);
    }
    @PutMapping("/{id}")
    public Avis modifierAvis(@PathVariable Integer id, @RequestBody Avis avis) {
        avis.setId(id);
        return avisSrv.updateAvis(avis);
    }
    @DeleteMapping("/{id}")
    public void supprimerAvis(@PathVariable Integer id) {
        avisSrv.deleteAvis(id);
    }
}
