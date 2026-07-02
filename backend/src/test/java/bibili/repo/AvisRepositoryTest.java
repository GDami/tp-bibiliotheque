package bibili.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import bibili.dao.IDAOAuteur;
import bibili.dao.IDAOAvis;
import bibili.dao.IDAOCollection;
import bibili.dao.IDAOEditeur;
import bibili.dao.IDAOLivre;
import bibili.model.Auteur;
import bibili.model.Avis;
import bibili.model.Collection;
import bibili.model.Editeur;
import bibili.model.Livre;

@DataJpaTest
public class AvisRepositoryTest {
    @Autowired
    private IDAOAvis repository;

    @Autowired
    private IDAOLivre livreRepository;

    @Autowired
    private IDAOEditeur editeurRepository;

    @Autowired
    private IDAOAuteur auteurRepository;

    @Autowired
    private IDAOCollection collectionRepository;

    private Livre createLivre() {
        Editeur editeur = editeurRepository.save(new Editeur(null, "Nom Editeur", "Pays Editeur"));
        Auteur auteur = auteurRepository.save(new Auteur(null, "Nom Auteur", "Prenom Auteur", "Nationalite Auteur"));
        Collection collection = collectionRepository.save(new Collection(null, "Nom Collection"));
        return livreRepository.save(new Livre(null, "Titre Livre", "Resume Livre", 2020, editeur, auteur, collection));
    }

    @Test
    public void testInsertAvis() {
        Livre livre = createLivre();
        Avis avis = new Avis(null, 3, "Je recommande ce livre", LocalDate.of(2026, 7, 2), livre);
        Avis savedAvis = repository.save(avis);

        assertNotNull(savedAvis.getId());
        assertEquals(3, savedAvis.getNote());
        assertEquals("Je recommande ce livre", savedAvis.getCommentaire());
        assertEquals("2026-07-02", savedAvis.getDateAvis().toString());
        assertNotNull(savedAvis.getLivre());
        assertEquals(livre.getId(), savedAvis.getLivre().getId());
    }

    @Test
    public void testFindAvisById() {
        Livre livre = createLivre();
        Avis avis = new Avis(null, 4, "Bonne lecture", LocalDate.of(2026, 7, 2), livre);
        Avis savedAvis = repository.save(avis);

        Avis foundAvis = repository.findById(savedAvis.getId()).orElse(null);

        assertNotNull(foundAvis);
        assertEquals(savedAvis.getId(), foundAvis.getId());
        assertEquals(savedAvis.getCommentaire(), foundAvis.getCommentaire());
    }

    @Test
    public void testUpdateAvis() {
        Livre livre = createLivre();
        Avis avis = new Avis(null, 2, "Moyen", LocalDate.of(2026, 7, 2), livre);
        Avis savedAvis = repository.save(avis);

        savedAvis.setCommentaire("Finalement excellent");
        Avis updatedAvis = repository.save(savedAvis);

        assertEquals("Finalement excellent", updatedAvis.getCommentaire());
    }

    @Test
    public void testDeleteAvis() {
        Livre livre = createLivre();
        Avis avis = new Avis(null, 1, "Pas terrible", LocalDate.of(2026, 7, 2), livre);
        Avis savedAvis = repository.save(avis);

        repository.delete(savedAvis);
        Avis deletedAvis = repository.findById(savedAvis.getId()).orElse(null);

        assertEquals(null, deletedAvis);
    }

    @Test
    public void testFindAllAviss() {
        Livre livre = createLivre();
        Avis avis1 = new Avis(null, 5, "Avis 1", LocalDate.of(2026, 7, 2), livre);
        Avis avis2 = new Avis(null, 3, "Avis 2", LocalDate.of(2026, 7, 2), livre);

        repository.save(avis1);
        repository.save(avis2);

        List<Avis> avis = repository.findAll();

        assertNotNull(avis);
        assertEquals(2, avis.size());
    }

    @Test
    public void testFindByIdWithLivres() {
        Livre livre = createLivre();
        Avis avis = new Avis(null, 4, "Avis avec livre", LocalDate.of(2026, 7, 2), livre);
        Avis savedAvis = repository.save(avis);

        Avis foundAvis = repository.findByIdWithLivres(savedAvis.getId());

        assertNotNull(foundAvis);
        assertEquals(savedAvis.getId(), foundAvis.getId());
        assertNotNull(foundAvis.getLivre());
        assertEquals(livre.getId(), foundAvis.getLivre().getId());
    }

}
