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

import bibili.model.Livre;
import bibili.service.LivreService;

@RestController
@RequestMapping("/api/livre")
public class LivreRestController {

    @Autowired
    private LivreService livreService;

    @GetMapping
    public List<Livre> chercherTous() {
        return livreService.getAll();
    }

    @GetMapping("/{id}")
    public Livre chercherParId(@PathVariable Integer id) {
        return livreService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        livreService.delete(id);
    }

    @PostMapping
    public Livre ajouter(@RequestBody Livre livre) {
        return livreService.insert(livre);
    }

    @PutMapping("/{id}")
    public Livre modifier(@PathVariable Integer id, @RequestBody Livre livre) {
        return livreService.update(livre);
    }
}
