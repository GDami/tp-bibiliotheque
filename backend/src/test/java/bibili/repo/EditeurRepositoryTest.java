package bibili.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import bibili.dao.IDAOEditeur;
import bibili.model.Editeur;

@DataJpaTest
public class EditeurRepositoryTest {

    @Autowired
    private IDAOEditeur repository;

    @Test
    public void testInsertEditeur() {
        Editeur editeur = new Editeur(null, "Nom Editeur", "Pays Editeur");
        Editeur savedEditeur = repository.save(editeur);

        assertNotNull(savedEditeur.getId());
        assertEquals("Nom Editeur", savedEditeur.getNom());
        assertEquals("Pays Editeur", savedEditeur.getPays());
    }

    @Test
    public void testFindEditeurById() {
        Editeur editeur = new Editeur(null, "Nom Editeur", "Pays Editeur");
        Editeur savedEditeur = repository.save(editeur);

        Editeur foundEditeur = repository.findById(savedEditeur.getId()).orElse(null);

        assertNotNull(foundEditeur);
        assertEquals(savedEditeur.getId(), foundEditeur.getId());
        assertEquals(savedEditeur.getNom(), foundEditeur.getNom());
        assertEquals(savedEditeur.getPays(), foundEditeur.getPays());
    }
 
    @Test
    public void testUpdateEditeur() {
        Editeur editeur = new Editeur(null, "Nom Editeur", "Pays Editeur");
        Editeur savedEditeur = repository.save(editeur);

        savedEditeur.setNom("Nouveau Nom");
        savedEditeur.setPays("Nouveau Pays");
        Editeur updatedEditeur = repository.save(savedEditeur);

        assertEquals("Nouveau Nom", updatedEditeur.getNom());
        assertEquals("Nouveau Pays", updatedEditeur.getPays());
    }

        @Test
    public void testDeleteEditeur() {
        Editeur editeur = new Editeur(null, "Nom Editeur", "Pays Editeur");
        Editeur savedEditeur = repository.save(editeur);

        repository.delete(savedEditeur);
        Editeur deletedEditeur = repository.findById(savedEditeur.getId()).orElse(null);

        assertEquals(null, deletedEditeur);
    }

        @Test
    public void testFindAllEditeurs() {
    Editeur editeur1 = new Editeur(null, "Nom Editeur 1", "Pays Editeur 1");
    Editeur editeur2 = new Editeur(null, "Nom Editeur 2", "Pays Editeur 2");

    repository.save(editeur1);
    repository.save(editeur2);

    List<Editeur> editeurs = repository.findAll();

    assertNotNull(editeurs);
    assertEquals(2, editeurs.size());
}


}