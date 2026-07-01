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

import bibili.model.Collection;
import bibili.service.CollectionService;

@RestController
@RequestMapping("/api/collection")
public class CollectionRestController {

    @Autowired
    CollectionService collectionSrv;

    @GetMapping
    public List<Collection> chercherTous() {
        return collectionSrv.getAll();
    }

    @GetMapping("/{id}")
    public Collection chercherParId(@PathVariable Integer id) {
        return collectionSrv.getById(id);
    }

    @GetMapping("/{id}/livres")
    public Collection chercherParIdAvecLivres(@PathVariable Integer id) {
        return collectionSrv.getByIdWithLivres(id);
    }

    @PostMapping
    public Collection ajouter(@RequestBody Collection collection) {
        return collectionSrv.insert(collection);
    }

    @PutMapping("/{id}")
    public Collection modifier(@PathVariable Integer id, @RequestBody Collection collection) {
        collection.setId(id);
        return collectionSrv.update(collection);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        collectionSrv.delete(id);
    }
}
